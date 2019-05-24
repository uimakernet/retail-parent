package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.repository.dao.mapper.OrderMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public OrderServiceImpl(OrderMapper mapper) {
        super(mapper);
        this.orderMapper = mapper;
    }
}
