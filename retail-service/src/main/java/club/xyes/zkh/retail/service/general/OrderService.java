package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.service.basic.AbstractService;
import com.github.pagehelper.PageInfo;

/**
 * Create by 郭文梁 2019/5/22 0022 16:52
 * OrderService
 * 订单相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/22 0022
 */
public interface OrderService extends AbstractService<Order> {
    /**
     * 通过用户ID查询订单记录
     *
     * @param userId 用户ID
     * @param page   页码
     * @param rows   每页大小
     * @return PageInfo with Orders
     */
    PageInfo<Order> findByUserId(Integer userId, Integer page, Integer rows);
}
