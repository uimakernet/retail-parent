package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.GeneralTimeRange;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.GeneralTimeRangeService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/28 0028 17:36
 * GeneralTimeRangeController
 * 通用时间区间相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@RestController
@RequestMapping("/api/general-time-range")
public class GeneralTimeRangeController extends AbstractEntityController<GeneralTimeRange> {
    private final GeneralTimeRangeService generalTimeRangeService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected GeneralTimeRangeController(GeneralTimeRangeService service) {
        super(service);
        this.generalTimeRangeService = service;
    }

    /**
     * 通过商品ID查询时间区间信息
     *
     * @param commodityId 商品ID
     * @return GR with GeneralTimeRange List
     */
    @GetMapping("/commodity/{commodityId}")
    public GeneralResult<List<GeneralTimeRange>> findByCommodity(@PathVariable("commodityId") Integer commodityId) {
        List<GeneralTimeRange> res = generalTimeRangeService.findByCommodityIdCollectByTimeRange(commodityId);
        return GeneralResult.ok(res);
    }
}
