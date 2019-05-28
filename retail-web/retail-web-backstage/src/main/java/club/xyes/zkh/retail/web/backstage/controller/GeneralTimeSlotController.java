package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.utils.ParamChecker;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.GeneralTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/28 0028 17:47
 * GeneralTimeSlotController
 * 通用时间段相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
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

    /**
     * 删除时间段
     *
     * @param id 时间段ID
     * @return GR with timeSlot
     */
    @DeleteMapping("/{id}")
    public GeneralResult<GeneralTimeSlot> delete(@PathVariable("id") Integer id) {
        @NotNull GeneralTimeSlot timeSlot = generalTimeSlotService.require(id);
        generalTimeSlotService.deleteById(timeSlot.getId());
        return GeneralResult.ok(timeSlot);
    }

    /**
     * 更新时间段信息
     *
     * @param id    ID
     * @param param 参数 （startTime,endTime, countUpperLimit）
     * @return GR
     */
    @PostMapping("/{id}")
    public GeneralResult<GeneralTimeSlot> update(@PathVariable("id") Integer id,
                                                 @RequestBody GeneralTimeSlot param) {
        @NotNull GeneralTimeSlot timeSlot = generalTimeSlotService.require(id);
        checkAndCopyUpdateParams(param, timeSlot);
        GeneralTimeSlot res = generalTimeSlotService.updateById(timeSlot);
        return GeneralResult.ok(res);
    }

    /**
     * 检查更新参数并且将参数拷贝到目标对象中
     *
     * @param param  更新参数
     * @param target 目标对象
     */
    private void checkAndCopyUpdateParams(GeneralTimeSlot param, GeneralTimeSlot target) {
        Class<? extends RuntimeException> exceptionClass = BadRequestException.class;
        ParamChecker.notNull(param, exceptionClass, "参数未传");
        ParamChecker.notNull(param.getStartTime(), exceptionClass, "开始时间未传");
        ParamChecker.notNull(param.getEndTime(), exceptionClass, "结束时间未传");
        ParamChecker.notNull(param.getCountUpperLimit(), exceptionClass, "接单量未传");
        target.setStartTime(param.getStartTime());
        target.setEndTime(param.getEndTime());
        target.setCountUpperLimit(param.getCountUpperLimit());
    }

}
