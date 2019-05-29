package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.commons.utils.DateTimeUtils;
import club.xyes.zkh.retail.repository.dao.mapper.SpecialTimeSlotMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

/**
 * Create by 郭文梁 2019/5/25 0025 10:56
 * SpecialTimeSlotServiceImpl
 * 特殊时间段定义相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Service
@Slf4j
public class SpecialTimeSlotServiceImpl extends AbstractServiceImpl<SpecialTimeSlot> implements SpecialTimeSlotService {
    private static final Date DEFAULT_START_TIME = DateTimeUtils.parseTime("00:00:00");
    private static final Date DEFAULT_END_TIME = DateTimeUtils.parseTime("23:59:59");
    private final SpecialTimeSlotMapper specialTimeSlotMapper;
    private static final int DEFAULT_COUON_UPPER_LIMIT = 0;

    public SpecialTimeSlotServiceImpl(SpecialTimeSlotMapper mapper) {
        super(mapper);
        this.specialTimeSlotMapper = mapper;
    }

    @Override
    public void init4Range(SpecialTimeRange range) {
        SpecialTimeSlot slot = new SpecialTimeSlot();
        slot.setTimeRangeId(range.getId());
        slot.setStartTime(DEFAULT_START_TIME);
        slot.setEndTime(DEFAULT_END_TIME);
        slot.setCountUpperLimit(DEFAULT_COUON_UPPER_LIMIT);
        slot.setBookedCount(0);
        save(slot);
        range.setSlots(Collections.singletonList(slot));
    }

    @Override
    public int deleteByRangeId(Integer rangeId) {
        SpecialTimeSlot query = new SpecialTimeSlot();
        query.setTimeRangeId(rangeId);
        return delete(query);
    }

    @Override
    public int countByRangeId(Integer timeRangeId) {
        SpecialTimeSlot query = new SpecialTimeSlot();
        query.setTimeRangeId(timeRangeId);
        return count(query);
    }

    @Override
    public SpecialTimeSlot create(Integer timeRangeId, SpecialTimeSlot target) {
        target.setTimeRangeId(timeRangeId);
        target.setBookedCount(0);
        return save(target);
    }
}
