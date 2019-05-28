package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SpecialTimeRangeController extends AbstractEntityController<SpecialTimeRange> {
    private final SpecialTimeRangeService specialTimeRangeService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected SpecialTimeRangeController(SpecialTimeRangeService service) {
        super(service);
        this.specialTimeRangeService = service;
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
}
