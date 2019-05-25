package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/25 0025 11:05
 * SpecialTimeRangeController
 * 特殊时间区间定义相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
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
}
