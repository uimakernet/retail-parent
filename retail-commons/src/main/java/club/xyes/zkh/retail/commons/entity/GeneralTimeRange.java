package club.xyes.zkh.retail.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create by 郭文梁 2019/5/18 0018 09:35
 * GeneralTimeRange
 * 通用预约时间区间定义
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_general_time_range")
public class GeneralTimeRange extends AbstractEntity {
    /**
     * 商品ID
     */
    @Column(name = "commodity_id", length = 10, nullable = false)
    private Integer commodityId;
    /**
     * 关联的Commodity对象
     */
    private Commodity commodity;
    /**
     * 星期几
     */
    @Column(name = "day_of_week", length = 1, nullable = false)
    private Integer dayOfWeek;
}
