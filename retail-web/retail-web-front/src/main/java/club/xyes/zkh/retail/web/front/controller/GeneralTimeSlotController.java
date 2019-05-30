package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.service.general.GeneralTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/25 0025 11:04
 * GeneralTimeSlotController
 * 普通时间段定义相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@RestController
@RequestMapping("/api/general-time-slot")
public class GeneralTimeSlotController extends AbstractEntityController<GeneralTimeSlot> {
    private final GeneralTimeSlotService generalTimeSlotService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected GeneralTimeSlotController(GeneralTimeSlotService service) {
        super(service);
        this.generalTimeSlotService = service;
    }
}
