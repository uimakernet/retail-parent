package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.service.basic.AbstractService;

/**
 * Create by 郭文梁 2019/5/25 0025 10:47
 * GeneralTimeSlotService
 * 普通时间段相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
public interface GeneralTimeSlotService extends AbstractService<GeneralTimeSlot> {
    /**
     * 通过时间区间查询时间段数量
     *
     * @param timeRangeId 区间ID
     * @return 时间段数量
     */
    int countByTimeRangeId(Integer timeRangeId);

    /**
     * 创建普通时间段
     *
     * @param rangeId 时间区间ID
     * @param slot    时间段参数
     * @return 创建结果
     */
    GeneralTimeSlot create(Integer rangeId, GeneralTimeSlot slot);
}
