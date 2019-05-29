package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.utils.ParamChecker;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.CommodityService;
import club.xyes.zkh.retail.service.general.StoreService;
import club.xyes.zkh.retail.web.commons.config.StaticConfigProp;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by 郭文梁 2019/5/20 0020 11:17
 * CommodityController
 * 商品相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@RestController
@RequestMapping("/api/commodity")
public class CommodityController extends AbstractEntityController<Commodity> {
    private final CommodityService commodityService;
    private final StaticConfigProp staticConfigProp;
    private final StoreService storeService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected CommodityController(CommodityService service,
                                  StaticConfigProp staticConfigProp,
                                  StoreService storeService) {
        super(service);
        this.commodityService = service;
        this.staticConfigProp = staticConfigProp;
        this.storeService = storeService;
    }

    /**
     * 分页获取全部商品
     *
     * @param page 页码
     * @param rows 每页大小
     * @return GR with Commodity list
     */
    @GetMapping("/all")
    public GeneralResult<PageInfo<Commodity>> all(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        PageInfo<Commodity> res = commodityService.list(page, rows);
        return GeneralResult.ok(prettyStaticPath(res));
    }

    /**
     * 获取商品详细信息
     *
     * @return GR
     */
    @GetMapping("/{id}")
    public GeneralResult<Commodity> detail(@PathVariable("id") Integer id) {
        @NotNull Commodity commodity = commodityService.requireFetchAll(id);
        prettyStaticPath(commodity);
        return GeneralResult.ok(commodity);
    }

    /**
     * 处理静态资源路径
     *
     * @param commodity 商品对象
     */
    private void prettyStaticPath(Commodity commodity) {
        String staticServer = staticConfigProp.getStaticServer();
        String commodityImagePath = staticConfigProp.getCommodityImagePath();
        commodity.parseImageList();
        commodity.setImageList(
                commodity.getImageList() == null ? null :
                        commodity.getImageList()
                                .stream()
                                .map(file -> staticServer + commodityImagePath + file)
                                .collect(Collectors.toList()));
    }

    /**
     * 处理静态资源路径
     *
     * @param commodities 商品对象列表
     * @return 处理结果
     */
    private List<Commodity> prettyStaticPath(List<Commodity> commodities) {
        return commodities == null ? null :
                commodities.stream()
                        .peek(this::prettyStaticPath)
                        .collect(Collectors.toList());
    }

    /**
     * 处理静态资源路径
     *
     * @param commodityPageInfo 商品分页对象
     * @return 分页对象
     */
    private PageInfo<Commodity> prettyStaticPath(PageInfo<Commodity> commodityPageInfo) {
        if (commodityPageInfo == null) {
            return null;
        }
        commodityPageInfo.setList(prettyStaticPath(commodityPageInfo.getList()));
        return commodityPageInfo;
    }

    /**
     * 创建信息商品
     *
     * @param storeId 店铺ID
     * @param param   参数
     * @return GR
     */
    @PostMapping("/store/{storeId}")
    public GeneralResult<Commodity> create(@PathVariable("storeId") Integer storeId,
                                           @RequestBody Commodity param) {
        Commodity commodity = new Commodity();
        checkAndCopy(param, commodity);
        @NotNull Store store = storeService.require(storeId);
        Commodity res = commodityService.create(storeId, commodity);
        res.setStore(store);
        return GeneralResult.ok(res);
    }

    /**
     * 检查并拷贝参数
     *
     * @param param  参数
     * @param target 拷贝目标
     */
    private void checkAndCopy(Commodity param, Commodity target) {
        Class<BadRequestException> exClass = BadRequestException.class;
        ParamChecker.notNull(param, exClass, "参数未传");
        ParamChecker.notNull(param.getName(), exClass, "商品名称必填");
        ParamChecker.notNull(param.getBuyLimit(), exClass, "每人购买上限必填");
        ParamChecker.notNull(param.getOriginalPrice(), exClass, "原价必填");
        ParamChecker.notNull(param.getCurrentPrice(), exClass, "现价必填");
        ParamChecker.notNull(param.getStockCount(), exClass, "库存必填");
        ParamChecker.notNull(param.getCommission1(), exClass, "一级返现必填");
        ParamChecker.notNull(param.getCommission2(), exClass, "二级返现必填");
        ParamChecker.notNull(param.getCommission3(), exClass, "三级返现必填");
        ParamChecker.notNull(param.getNeedAppointment(), exClass, "请指定是否需要预约");
        ParamChecker.notNull(param.getBuyLimit(), exClass, "抢购结束时间必填");
        target.setName(param.getName());
        target.setDescription(param.getDescription());
        target.setBuyLimit(param.getBuyLimit());
        target.setOriginalPrice(param.getOriginalPrice());
        target.setCurrentPrice(param.getCurrentPrice());
        target.setStockCount(param.getStockCount());
        target.setCommission1(param.getCommission1());
        target.setCommission2(param.getCommission2());
        target.setCommission3(param.getCommission3());
        target.setNeedAppointment(param.getNeedAppointment());
        target.setBuyEndTime(param.getBuyEndTime());
        if (param.getNeedAppointment()) {
            ParamChecker.notNull(param.getAppointmentStartTime(), exClass, "请指定预约开始时间");
            ParamChecker.notNull(param.getAppointmentEndTime(), exClass, "请指定预约结束时间");
            target.setAppointmentStartTime(param.getAppointmentStartTime());
            target.setAppointmentEndTime(param.getAppointmentEndTime());
        }
    }

    /**
     * 查询某商家的商品
     *
     * @param storeId 商铺ID
     * @param page    页码
     * @param rows    每页大小
     * @return GR with PageInfo
     */
    @GetMapping("/store/{storeId}")
    public GeneralResult<PageInfo<Commodity>> findByStoreId(@PathVariable("storeId") Integer storeId,
                                                            Integer page, Integer rows) {
        Store store = storeService.require(storeId);
        PageInfo<Commodity> commodityPageInfo = commodityService.findByStoreId(storeId, defaultPage(page), defaultRows(rows));
        commodityPageInfo.setList(commodityPageInfo.getList()
                .stream()
                .peek(c -> c.setStore(store))
                .collect(Collectors.toList()));
        return GeneralResult.ok(commodityPageInfo);
    }
}
