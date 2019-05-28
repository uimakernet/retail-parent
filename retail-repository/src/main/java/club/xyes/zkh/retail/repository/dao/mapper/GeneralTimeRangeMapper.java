package club.xyes.zkh.retail.repository.dao.mapper;

import club.xyes.zkh.retail.commons.entity.GeneralTimeRange;
import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/25 0025 10:14
 * GeneralTimeRangeMapper
 * 普通时间区间定义相关数据库访问对象
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Repository
public interface GeneralTimeRangeMapper extends AbstractMapper<GeneralTimeRange> {
    /**
     * 通过商品ID查询预约时间段 同时关联查询出时间区间信息
     *
     * @param commodityId 商品ID
     * @return 时间段列表
     */
    List<GeneralTimeSlot> selectByCommodityId(@Param("commodityId") Integer commodityId);
}
