package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.GeneralTimeRange;
import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.repository.dao.mapper.GeneralTimeRangeMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.GeneralTimeRangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 郭文梁 2019/5/25 0025 10:52
 * GeneralTimeRangeServiceImpl
 * 普通时间区间相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Service
@Slf4j
public class GeneralTimeRangeServiceImpl extends AbstractServiceImpl<GeneralTimeRange> implements GeneralTimeRangeService {
    private final GeneralTimeRangeMapper generalTimeRangeMapper;

    public GeneralTimeRangeServiceImpl(GeneralTimeRangeMapper mapper) {
        super(mapper);
        this.generalTimeRangeMapper = mapper;
    }

    @Override
    public List<GeneralTimeSlot> findByCommodityIdWithTimeRange(Integer commodityId) {
        return generalTimeRangeMapper.selectByCommodityId(commodityId);
    }

    @Override
    public List<GeneralTimeRange> findByCommodityIdCollectByTimeRange(Integer commodityId) {
        List<GeneralTimeSlot> slots = findByCommodityIdWithTimeRange(commodityId);
        Map<Integer, GeneralTimeRange> rangeMap = new HashMap<>(7);
        slots.forEach(slot -> {
            GeneralTimeRange range = rangeMap.computeIfAbsent(slot.getTimeRangeId(), k -> slot.getGeneralTimeRange());
            List<GeneralTimeSlot> rangeSlots = range.getSlots();
            if (rangeSlots == null) {
                rangeSlots = new ArrayList<>();
                range.setSlots(rangeSlots);
            }
            rangeSlots.add(slot);
            slot.setGeneralTimeRange(null);
        });
        return new ArrayList<>(rangeMap.values());
    }
}
