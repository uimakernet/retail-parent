package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.GeneralTimeRange;
import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.service.basic.AbstractService;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/25 0025 10:46
 * GeneralTimeRangeService
 * 普通时间区间定义相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
public interface GeneralTimeRangeService extends AbstractService<GeneralTimeRange> {
    /**
     * 通过商品ID查询时间段 同时查询出时间区间信息
     *
     * @param commodityId 商品ID
     * @return TimeSlot list
     */
    List<GeneralTimeSlot> findByCommodityIdWithTimeRange(Integer commodityId);

    /**
     * 通过商品ID查询 查询结果通过GeneralTime
     * @param commodityId 商品ID
     * @return GeneralTimeRange with GeneralTimeSlot list
     */
    List<GeneralTimeRange> findByCommodityIdCollectByTimeRange(Integer commodityId);
}
