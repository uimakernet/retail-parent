package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.service.basic.AbstractService;

/**
 * Create by 郭文梁 2019/5/25 0025 10:49
 * SpecialTimeSlotService
 * 特殊时间段相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
public interface SpecialTimeSlotService extends AbstractService<SpecialTimeSlot> {
    /**
     * 为时间区间创建默认的时间段
     *
     * @param range 时间区间
     */
    void init4Range(SpecialTimeRange range);

    /**
     * 通过区间ID删除时间段
     *
     * @param rangeId 区间ID
     * @return 删除时间段数量
     */
    int deleteByRangeId(Integer rangeId);

    /**
     * 通过时间区间ID查询时间段数量
     *
     * @param timeRangeId 区间ID
     * @return 时间段数量
     */
    int countByRangeId(Integer timeRangeId);

    /**
     * 为时间区间创建时间段定义
     *
     * @param timeRangeId 时间区间ID
     * @param target      参数
     * @return 创建结果
     */
    SpecialTimeSlot create(Integer timeRangeId, SpecialTimeSlot target);
}
