package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.utils.ParamChecker;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.*;

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
    private final SpecialTimeRangeService specialTimeRangeService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected SpecialTimeSlotController(SpecialTimeSlotService service,
                                        SpecialTimeRangeService specialTimeRangeService) {
        super(service);
        this.specialTimeSlotService = service;
        this.specialTimeRangeService = specialTimeRangeService;
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
        int count = specialTimeSlotService.countByRangeId(timeSlot.getTimeRangeId());
        if (count <= 1) {
            throw new BadRequestException("请至少保留一个时间段");
        }
        specialTimeSlotService.deleteById(id);
        return GeneralResult.ok(timeSlot);
    }

    /**
     * 为特殊时间区间创建时间段
     *
     * @param timeRangeId 时间区间ID
     * @param param       参数
     * @return GR
     */
    @PostMapping("/range/{specialTimeRangeId}")
    public GeneralResult<SpecialTimeSlot> create(@PathVariable("specialTimeRangeId") Integer timeRangeId,
                                                 @RequestBody SpecialTimeSlot param) {
        @NotNull SpecialTimeRange range = specialTimeRangeService.require(timeRangeId);
        SpecialTimeSlot target = new SpecialTimeSlot();
        checkAndCopy(param, target);
        SpecialTimeSlot res = specialTimeSlotService.create(timeRangeId, target);
        res.setSpecialTimeRange(range);
        return GeneralResult.ok(res);
    }

    /**
     * 检查并且拷贝参数
     *
     * @param param  参数
     * @param target 拷贝目标
     */
    private void checkAndCopy(SpecialTimeSlot param, SpecialTimeSlot target) {
        Class<BadRequestException> exClass = BadRequestException.class;
        ParamChecker.notNull(param, exClass, "参数未传");
        ParamChecker.notNull(param.getStartTime(), exClass, "开始时间必填");
        ParamChecker.notNull(param.getEndTime(), exClass, "结束时间必填");
        ParamChecker.notNull(param.getCountUpperLimit(), exClass, "预约上限必填");
        target.setStartTime(param.getStartTime());
        target.setEndTime(param.getEndTime());
        target.setCountUpperLimit(param.getCountUpperLimit());
    }

    /**
     * 更新时间段信息
     *
     * @param id    ID
     * @param param 更新参数
     * @return GR
     */
    @PostMapping("/{id}")
    public GeneralResult update(@PathVariable("id") Integer id,
                                @RequestBody SpecialTimeSlot param) {
        @NotNull SpecialTimeSlot target = specialTimeSlotService.require(id);
        checkAndCopy(param, target);
        SpecialTimeSlot res = specialTimeSlotService.updateById(target);
        return GeneralResult.ok(res);
    }
}
