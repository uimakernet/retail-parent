package club.xyes.zkh.retail.service.listener;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.utils.CommissionUtil;
import club.xyes.zkh.retail.commons.utils.OrderUtils;
import club.xyes.zkh.retail.service.general.CommodityService;
import club.xyes.zkh.retail.service.general.OrderService;
import club.xyes.zkh.retail.service.general.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Create by 郭文梁 2019/5/30 0030 10:17
 * PaySuccessListenerImpl
 * 支付成功监听器实现
 *
 * @author 郭文梁
 * @data 2019/5/30 0030
 */
@Component
@Slf4j
public class PaySuccessListenerImpl implements OrderService.PaySuccessListener {
    private final OrderService orderService;
    private final CommodityService commodityService;
    private final UserService userService;

    public PaySuccessListenerImpl(OrderService orderService,
                                  CommodityService commodityService,
                                  UserService userService) {
        this.orderService = orderService;
        this.commodityService = commodityService;
        this.userService = userService;
    }


    @Override
    public void onPaySuccess(Order order) {
        log.debug("Pay success {}", order);
        @NotNull Commodity commodity = commodityService.require(order.getCommodityId());
        updateOrderStatus(order, commodity);
        doReturnCommission(order, commodity);
    }

    /**
     * 执行推广返现逻辑
     *
     * @param order     订单信息
     * @param commodity 商品信息
     */
    private void doReturnCommission(Order order, Commodity commodity) {
        if (order.getPromoterId() == null) {
            log.info("Important Information : no promoter for order:" + order.getId());
            return;
        }
        //直接推广用户
        User promoter = userService.get(order.getPromoterId());
        //返现规则列表
        List<Integer> commissionAmountList = CommissionUtil.getCommissionAmountList(promoter, commodity);
        //一级返现 存入推手的直接收益
        doReturnCommission(commissionAmountList.get(0), promoter);
        //二三级返现 存入用户的团队收益
        doReturnTeamCommission(promoter, commissionAmountList);
    }

    /**
     * 执行二三级返现
     *
     * @param promoter       直接推手
     * @param commissionList 返现规则列表
     */
    private void doReturnTeamCommission(User promoter, List<Integer> commissionList) {
        User captain = promoter;
        //由于第一条返现规则是直接推广返现规则 故这里从第二条开始算
        for (int i = 1; i < commissionList.size(); i++) {
            if (captain.getLeaderId() == null) {
                //上级队长不存在 不再继续执行返现
                break;
            }
            Integer commission = commissionList.get(i);
            captain = userService.require(captain.getLeaderId());
            Integer originalTeamIncome = captain.getTeamIncome();
            int income = CommissionUtil.add(originalTeamIncome, commission);
            captain.setTeamIncome(income);
            userService.updateById(captain);
            log.info("Important Commission Information : Do Return 2,3 Commission, commission={},original income={},result income={}", commission, originalTeamIncome, income);
        }
    }

    /**
     * 执行一级推广返现
     *
     * @param commission 返现金额
     * @param promoter   直接推广者
     */
    private void doReturnCommission(Integer commission, User promoter) {
        //用户的直接收益
        Integer directIncome = promoter.getDirectIncome();
        //与本次推广收益相加
        int resultIncome = CommissionUtil.add(directIncome, commission);
        //设置为新的直接推广收益
        promoter.setDirectIncome(resultIncome);
        //更新数据库
        userService.updateById(promoter);
        log.info("Important Commission Information : Do Return Direct Commission, commission={},original income={},result income={}", commission, directIncome, resultIncome);
    }

    /**
     * 更新订单状态
     *
     * @param commodity 商品信息
     * @param order     订单
     */
    private void updateOrderStatus(Order order, Commodity commodity) {
        if (OrderUtils.needAppointment(commodity)) {
            order.setStatus(Order.STATUS_NEED_BOOKED);
        } else {
            order.setStatus(Order.STATUS_PAID);
        }
        orderService.updateById(order);
    }
}
