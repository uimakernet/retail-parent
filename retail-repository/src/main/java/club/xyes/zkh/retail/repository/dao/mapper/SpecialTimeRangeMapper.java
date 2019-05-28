package club.xyes.zkh.retail.repository.dao.mapper;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/25 0025 10:16
 * SpecialTimeRangeMapper
 * 特殊时间区间相关数据库访问对象
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Repository
public interface SpecialTimeRangeMapper extends AbstractMapper<SpecialTimeRange> {
    /**
     * 通过商品ID查询特殊时间段定义 同时关联查询出时间区间的信息
     *
     * @param commodityId 商品ID
     * @return 特殊时间段定义
     */
    List<SpecialTimeSlot> selectByCommodityIdFetchRange(@Param("commodityId") Integer commodityId);
}
