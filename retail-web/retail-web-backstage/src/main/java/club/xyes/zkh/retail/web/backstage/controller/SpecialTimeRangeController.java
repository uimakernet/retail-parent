package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.utils.ParamChecker;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.CommodityService;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Create by 郭文梁 2019/5/28 0028 18:19
 * SpecialTimeRangeController
 * 特殊时间区间定义相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@RestController
@RequestMapping("/api/special-time-range")
@Slf4j
public class SpecialTimeRangeController extends AbstractEntityController<SpecialTimeRange> {
    private final SpecialTimeRangeService specialTimeRangeService;
    private final CommodityService commodityService;
    private final SpecialTimeSlotService specialTimeSlotService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected SpecialTimeRangeController(SpecialTimeRangeService service,
                                         CommodityService commodityService,
                                         SpecialTimeSlotService specialTimeSlotService) {
        super(service);
        this.specialTimeRangeService = service;
        this.commodityService = commodityService;
        this.specialTimeSlotService = specialTimeSlotService;
    }

    /**
     * 通过商品查询特殊预约时间区间定义
     *
     * @param commodityId 商品ID
     * @return GR with time range list
     */
    @GetMapping("/commodity/{commodityId}")
    public GeneralResult<List<SpecialTimeRange>> findByCommodity(@PathVariable("commodityId") Integer commodityId) {
        List<SpecialTimeRange> res = specialTimeRangeService.findByCommodityIdCollectByTimeRange(commodityId);
        return GeneralResult.ok(res);
    }

    /**
     * 为商品创建新的特殊时间区间
     *
     * @param commodityId 商品ID
     * @param param       请求参数
     * @return GR
     */
    @PostMapping("/commodity/{commodityId}")
    public GeneralResult<SpecialTimeRange> create(@PathVariable("commodityId") Integer commodityId,
                                                  @RequestBody SpecialTimeRange param) {
        SpecialTimeRange target = new SpecialTimeRange();
        checkAndCopyParam(param, target);
        ParamChecker.notNull(commodityId, BadRequestException.class, "商品ID未传");
        commodityService.requireExistsById(commodityId);
        SpecialTimeRange res = specialTimeRangeService.create(commodityId, target, specialTimeSlotService::init4Range);
        return GeneralResult.ok(res);
    }

    /**
     * 检查并拷贝参数
     *
     * @param param  参数
     * @param target 拷贝目的地
     */
    private void checkAndCopyParam(SpecialTimeRange param, SpecialTimeRange target) {
        Class<BadRequestException> exceptionClass = BadRequestException.class;
        ParamChecker.notNull(param, exceptionClass, "参数未传");
        ParamChecker.notNull(param.getActionDate(), exceptionClass, "作用日期未传");
        target.setActionDate(param.getActionDate());
    }

    /**
     * 删除特殊时间区间
     *
     * @param id 区间ID
     * @return GR
     */
    @DeleteMapping("/{id}")
    public GeneralResult<SpecialTimeRange> delete(@PathVariable("id") Integer id) {
        @NotNull SpecialTimeRange range = specialTimeRangeService.require(id);
        int deletedRows = specialTimeSlotService.deleteByRangeId(id);
        log.info("Deleted {} special time slot for range {}", deletedRows, id);
        specialTimeRangeService.deleteById(id);
        return GeneralResult.ok(range);
    }
}
