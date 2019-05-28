package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.CashApplication;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.service.basic.AbstractService;

/**
 * Create by 郭文梁 2019/5/25 0025 10:46
 * CashApplicationService
 * 提现申请相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
public interface CashApplicationService extends AbstractService<CashApplication> {
    /**
     * 创建新的提现申请
     *
     * @param user   用户
     * @param amount 提现金额
     * @return 提现申请对象
     */
    CashApplication create(User user, Integer amount, OnApplicationCreateSuccessListsner listsner);

    /**
     * 提现申请创建成功监听
     */
    interface OnApplicationCreateSuccessListsner {
        /**
         * 提现申请创建成功监听
         *
         * @param application 申请对象
         */
        void onApplicationCreateSuccess(CashApplication application);
    }
}
