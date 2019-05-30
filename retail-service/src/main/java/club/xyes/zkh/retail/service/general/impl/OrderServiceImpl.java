package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.commons.utils.OrderUtils;
import club.xyes.zkh.retail.commons.utils.RandomUtils;
import club.xyes.zkh.retail.repository.dao.mapper.OrderMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.OrderService;
import club.xyes.zkh.retail.wechat.api.Wechat;
import club.xyes.zkh.retail.wechat.dto.WxTradeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/22 0022 16:53
 * OrderServiceImpl
 * 订单相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/22 0022
 */
@Service
@Slf4j
public class OrderServiceImpl extends AbstractServiceImpl<Order> implements OrderService {
    private final OrderMapper orderMapper;
    private final Wechat wechat;

    public OrderServiceImpl(OrderMapper mapper,
                            Wechat wechat) {
        super(mapper);
        this.orderMapper = mapper;
        this.wechat = wechat;
    }

    @Override
    public PageInfo<Order> findByUserId(Integer userId, Integer page, Integer rows) {
        return PageHelper.startPage(page, rows).doSelectPageInfo(() -> orderMapper.selectByUserId(userId));
    }

    @Override
    public PageInfo<Order> findByUserIdAndStatus(Integer userId, Integer status, Integer page, Integer rows) {
        return PageHelper.startPage(page, rows).doSelectPageInfo(() -> orderMapper.selectByUserIdAndStatus(userId, status));
    }

    @Override
    public Order create(Order order) {
        snapshot(order.getCommodity(), order);
        identify(order);
        calculatePrice(order);
        order.setStatus(Order.STATUS_CREATE);
        return save(order);
    }

    @Override
    public Order refreshStatus(Integer id, PaySuccessListener listener) {
        @NotNull Order order = require(id);
        if (OrderUtils.isPaid(order)) {
            //若已支付成功 不再执行后续流程
            return order;
        }
        WxTradeInfo wxTradeInfo = wechat.queryTrade(order);
        log.info("Query trade [{}] for order {}", wxTradeInfo, order);
        if (wxTradeInfo.isPaid()) {
            if (listener != null) {
                listener.onPaySuccess(order);
            }
        }
        return order;
    }

    /**
     * 初始化订单状态
     *
     * @param order     订单
     * @param commodity 商品
     */
    private void initStatus(Order order, Commodity commodity) {
        if (commodity.getNeedAppointment()) {
            order.setStatus(Order.STATUS_NEED_BOOKED);
        } else {
            order.setStatus(Order.STATUS_PAID);
        }
    }

    /**
     * 计算价格
     *
     * @param order 订单对象
     */
    private void calculatePrice(Order order) {
        Commodity commodity = order.getCommodity();
        Integer price = commodity.getCurrentPrice();
        Integer quantity = order.getQuantity();
        int amount = price * quantity;
        order.setAmount(amount);
    }

    /**
     * 为订单生成唯一标识
     *
     * @param order 订单对象
     */
    private void identify(Order order) {
        String sn = RandomUtils.randomPrettyUUIDString();
        String tradeNo = RandomUtils.randomPrettyUUIDString();
        order.setSn(sn);
        order.setTradeNo(tradeNo);
    }

    /**
     * 商品信息快照
     *
     * @param commodity 商品对象
     * @param order     订单对象
     */
    private void snapshot(Commodity commodity, Order order) {
        order.setCommodityName(commodity.getName());
        order.setCommodityDescription(commodity.getDescription());
    }
}
