package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.CommodityService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import club.xyes.zkh.retail.web.front.config.StaticConfigProp;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected CommodityController(CommodityService service,
                                  StaticConfigProp staticConfigProp) {
        super(service);
        this.commodityService = service;
        this.staticConfigProp = staticConfigProp;
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
}
