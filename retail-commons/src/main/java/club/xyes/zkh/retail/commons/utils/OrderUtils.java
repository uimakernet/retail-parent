package club.xyes.zkh.retail.commons.utils;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.entity.Order;

/**
 * Create by 郭文梁 2019/5/30 0030 10:11
 * OrderUtils
 * 订单相关工具类
 *
 * @author 郭文梁
 * @data 2019/5/30 0030
 */
public class OrderUtils {
    /**
     * 检查订单是否已经支付
     *
     * @param order 订单
     * @return 是否已经支付
     */
    public static boolean isPaid(Order order) {
        Integer status = order.getStatus();
        switch (status) {
            case Order.STATUS_PAID:
            case Order.STATUS_NEED_BOOKED:
            case Order.STATUS_BOOKED:
            case Order.STATUS_COMPLETE:
                return true;
            default:
                return false;
        }
    }

    /**
     * 商品是否需要预约
     *
     * @param commodity 商品
     * @return 是否需要预约
     */
    public static boolean needAppointment(Commodity commodity) {
        return commodity.getNeedAppointment();
    }
}
