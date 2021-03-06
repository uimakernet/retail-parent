package club.xyes.zkh.retail.commons.entity;

import club.xyes.zkh.retail.commons.context.ApplicationConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Create by 郭文梁 2019/5/18 0018 09:59
 * SpecialTimeRange
 * 特殊预约时间区间定义
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_special_time_range")
public class SpecialTimeRange extends AbstractEntity {
    /**
     * 商品ID
     */
    @Column(name = "commodity_id", nullable = false, length = 10)
    private Integer commodityId;
    /**
     * 关联的商品对象
     */
    private Commodity commodity;
    /**
     * 日期
     */
    @Column(name = "action_date", nullable = false)
    @JsonFormat(pattern = ApplicationConstants.DATE_FORMAT)
    private Date actionDate;
    /**
     * 预约时间段列表
     */
    private List<SpecialTimeSlot> slots;
}
