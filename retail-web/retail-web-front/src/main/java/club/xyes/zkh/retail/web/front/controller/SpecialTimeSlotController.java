package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/25 0025 11:07
 * SpecialTimeSlotController
 * 特殊时间段定义相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@RestController
@RequestMapping("/api/special-time-slot")
public class SpecialTimeSlotController extends AbstractEntityController<SpecialTimeSlot> {
    private final SpecialTimeSlotService specialTimeSlotService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected SpecialTimeSlotController(SpecialTimeSlotService service) {
        super(service);
        this.specialTimeSlotService = service;
    }
}
