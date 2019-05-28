package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/28 0028 18:26
 * SpecialTimeSlotController
 * 特殊时间段相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
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

    /**
     * 删除特殊时间段定义
     *
     * @param id ID
     * @return GR
     */
    @DeleteMapping("/{id}")
    public GeneralResult delete(@PathVariable("id") Integer id) {
        @NotNull SpecialTimeSlot timeSlot = specialTimeSlotService.require(id);
        specialTimeSlotService.deleteById(id);
        return GeneralResult.ok(timeSlot);
    }
}
