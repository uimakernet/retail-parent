package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.repository.dao.mapper.SpecialTimeRangeMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 郭文梁 2019/5/25 0025 10:54
 * SpecialTimeRangeServiceImpl
 * 特殊时间段相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Service
@Slf4j
public class SpecialTimeRangeServiceImpl extends AbstractServiceImpl<SpecialTimeRange> implements SpecialTimeRangeService {
    private final SpecialTimeRangeMapper specialTimeRangeMapper;

    public SpecialTimeRangeServiceImpl(SpecialTimeRangeMapper mapper) {
        super(mapper);
        this.specialTimeRangeMapper = mapper;
    }

    @Override
    public List<SpecialTimeSlot> findByCommodityIdWithTimeRange(Integer commodityId) {
        return specialTimeRangeMapper.selectByCommodityIdFetchRange(commodityId);
    }

    @Override
    public List<SpecialTimeRange> findByCommodityIdCollectByTimeRange(Integer commodityId) {
        List<SpecialTimeSlot> slots = findByCommodityIdWithTimeRange(commodityId);
        Map<Integer, SpecialTimeRange> rangeMap = new HashMap<>(7);
        slots.forEach(slot -> {
            SpecialTimeRange range = rangeMap.computeIfAbsent(slot.getTimeRangeId(), k -> slot.getSpecialTimeRange());
            List<SpecialTimeSlot> rangeSlots = range.getSlots();
            if (rangeSlots == null) {
                rangeSlots = new ArrayList<>();
                range.setSlots(rangeSlots);
            }
            rangeSlots.add(slot);
            slot.setSpecialTimeRange(null);
        });
        return new ArrayList<>(rangeMap.values());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SpecialTimeRange create(Integer commodityId, SpecialTimeRange target, OnCreateListener listener) {
        target.setCommodityId(commodityId);
        SpecialTimeRange res = save(target);
        if (listener != null) {
            listener.onSpecialTimeRangeCreate(res);
        }
        return res;
    }
}
