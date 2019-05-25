package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.SpecialTimeSlot;
import club.xyes.zkh.retail.repository.dao.mapper.SpecialTimeSlotMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.SpecialTimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final SpecialTimeSlotMapper specialTimeSlotMapper;

    public SpecialTimeSlotServiceImpl(SpecialTimeSlotMapper mapper) {
        super(mapper);
        this.specialTimeSlotMapper = mapper;
    }
}
