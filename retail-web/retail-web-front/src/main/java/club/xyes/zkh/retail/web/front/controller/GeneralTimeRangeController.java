package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.GeneralTimeRange;
import club.xyes.zkh.retail.service.general.GeneralTimeRangeService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/25 0025 10:59
 * GeneralTimeRangeController
 * 普通时间区间相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
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
}
