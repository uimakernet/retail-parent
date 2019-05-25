package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.CashApplication;
import club.xyes.zkh.retail.repository.dao.mapper.CashApplicationMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.CashApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Create by 郭文梁 2019/5/25 0025 10:50
 * CashApplicationServiceImpl
 * 提现申请相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@Service
@Slf4j
public class CashApplicationServiceImpl extends AbstractServiceImpl<CashApplication> implements CashApplicationService {
    private final CashApplicationMapper generalTimeRangeMapper;

    public CashApplicationServiceImpl(CashApplicationMapper mapper) {
        super(mapper);
        this.generalTimeRangeMapper = mapper;
    }
}
