package club.xyes.zkh.retail.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Create by 郭文梁 2019/5/18 0018 09:52
 * GeneralTimeSlot
 * 通用时间段实体类
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_general_time_slot")
public class GeneralTimeSlot extends AbstractEntity {
    /**
     * 预约时间区间ID
     */
    @Column(name = "time_range_id", length = 10, nullable = false)
    private Integer timeRangeId;
    /**
     * 关联的时间区间对象
     */
    private GeneralTimeRange generalTimeRange;
    /**
     * 开始时间
     */
    @Column(name = "start_time", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**
     * 结束时间
     */
    @Column(name = "end_time", nullable = false)
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 接单量上限
     */
    @Column(name = "count_upper_limit", length = 5, nullable = false)
    private Integer countUpperLimit;
    /**
     * 已接单量
     */
    @Column(name = "booked_count", length = 5, nullable = false)
    private Integer bookedCount;
}
