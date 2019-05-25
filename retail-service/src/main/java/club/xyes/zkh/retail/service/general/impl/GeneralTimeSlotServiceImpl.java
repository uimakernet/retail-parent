package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.GeneralTimeSlot;
import club.xyes.zkh.retail.repository.dao.mapper.GeneralTimeSlotMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.GeneralTimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Create by 郭文梁 2019/5/25 0025 10:53
 * GeneralTimeSlotServiceImpl
 * 普通时间段相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Service
@Slf4j
public class GeneralTimeSlotServiceImpl extends AbstractServiceImpl<GeneralTimeSlot> implements GeneralTimeSlotService {
    private final GeneralTimeSlotMapper generalTimeSlotMapper;

    public GeneralTimeSlotServiceImpl(GeneralTimeSlotMapper mapper) {
        super(mapper);
        this.generalTimeSlotMapper = mapper;
    }
}
