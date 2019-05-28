package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.CashApplication;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.utils.WithdrawUtils;
import club.xyes.zkh.retail.repository.dao.mapper.CashApplicationMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.CashApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CashApplication create(User user, Integer amount, OnApplicationCreateSuccessListsner listsner) {
        int withdrawableAmount = WithdrawUtils.getWithdrawableAmount(user);
        if (amount > withdrawableAmount) {
            throw new BadRequestException("输入金额必须小于可提现金额");
        }
        CashApplication application = new CashApplication();
        application.setUser(user);
        application.setUserId(user.getId());
        application.setAmount(amount);
        application.setStatus(CashApplication.STATUS_CREATE);
        CashApplication saveResult = save(application);
        if (listsner != null) {
            listsner.onApplicationCreateSuccess(saveResult);
        }
        return saveResult;
    }
}
