package club.xyes.zkh.retail.commons.utils;

import club.xyes.zkh.retail.commons.entity.User;

import java.util.Objects;

/**
 * Create by 郭文梁 2019/5/25 0025 11:11
 * WithdrawUtils
 * 提现相关工具类
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
public class WithdrawUtils {
    /**
     * 获取可提现金额
     *
     * @param user 用户对象
     * @return 可提现金额
     */
    public static int getWithdrawableAmount(User user) {
        int amount;
        switch (user.getRole()) {
            case User.ROLE_USER:
                amount = 0;
                break;
            case User.ROLE_PROMOTERS:
                amount = user.getDirectIncome();
                break;
            case User.ROLE_CAPTAIN:
                amount = getCaptainWithdrawableAmount(user);
                break;
            default:
                throw new IllegalArgumentException("Unknown role" + user.getRole() + " for user " + user);
        }
        return amount;
    }

    /**
     * 获取队长的可提现金额
     *
     * @param user 用户对象
     * @return 可提现金额
     */
    public static int getCaptainWithdrawableAmount(User user) {
        if (Objects.equals(user.getRole(), User.ROLE_CAPTAIN)) {
            int amount;
            switch (user.getTeamHeaderLevel()) {
                case User.LEVEL_PRIMARY:
                    amount = user.getDirectIncome();
                    break;
                case User.LEVEL_MIDDLE:
                case User.LEVEL_SENIOR:
                    amount = user.getDirectIncome() + user.getTeamIncome();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown teamHeaderLevel " + user.getTeamHeaderLevel() + " for user " + user);
            }
            return amount;
        } else {
            throw new IllegalArgumentException("User " + user + " is not a Captain!");
        }
    }
}
