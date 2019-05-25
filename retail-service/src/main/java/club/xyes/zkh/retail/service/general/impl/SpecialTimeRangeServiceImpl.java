package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.SpecialTimeRange;
import club.xyes.zkh.retail.repository.dao.mapper.SpecialTimeRangeMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.SpecialTimeRangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
