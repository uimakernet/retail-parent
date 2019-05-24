package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.OrderService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/22 0022 17:05
 * OrderController
 * 订单相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/22 0022
 */
@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractEntityController<Order> {
    private final OrderService orderService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected OrderController(OrderService service) {
        super(service);
        this.orderService = service;
    }

    @GetMapping("/find-by-current-user")
    public GeneralResult<PageInfo<Order>> findByCurrentUser() {
        return null;
    }
}
