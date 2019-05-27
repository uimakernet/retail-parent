package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.CommodityService;
import club.xyes.zkh.retail.service.general.OrderService;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import club.xyes.zkh.retail.web.front.vo.OrderCreateVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static club.xyes.zkh.retail.commons.utils.ParamChecker.notNull;

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
    private final UserService userService;
    private final OrderService orderService;
    private final CommodityService commodityService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected OrderController(OrderService service,
                              UserService userService,
                              CommodityService commodityService) {
        super(service);
        this.orderService = service;
        this.userService = userService;
        this.commodityService = commodityService;
    }

    /**
     * 查询当前用户的订单记录
     *
     * @param page 页码
     * @param rows 每页大小
     * @return GR with Order PageInfo Object
     */
    @GetMapping("/my/all")
    public GeneralResult<PageInfo<Order>> findByCurrentUser(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        Integer userId = requireUserInfo().getUserId();
        PageInfo<Order> orders = orderService.findByUserId(userId, page, rows);
        return GeneralResult.ok(orders);
    }

    /**
     * 通过状态查询当前用户的订单
     *
     * @param status 状态
     * @param page   页码
     * @param rows   每页大小
     * @return Gr with Order PageInfo
     */
    @GetMapping("/my/status/{status}")
    public GeneralResult<PageInfo<Order>> findByCurrentUserAndStatus(@PathVariable("status") Integer status,
                                                                     Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        Integer userId = requireUserInfo().getUserId();
        PageInfo<Order> orders = orderService.findByUserIdAndStatus(userId, status, page, rows);
        return GeneralResult.ok(orders);
    }

    /**
     * 创建订单
     *
     * @param param 参数
     * @return GR with order info
     */
    @PostMapping("/create")
    public GeneralResult<Order> create(@RequestBody OrderCreateVo param) {
        notNull(param, BadRequestException.class, "参数未传");
        notNull(param.getCommodityId(), BadRequestException.class, "商品ID必填");
        notNull(param.getPhone(), BadRequestException.class, "电话必填");
        notNull(param.getQuantity(), BadRequestException.class, "购买数量必填");
        notNull(param.getUsername(), BadRequestException.class, "姓名必填");
        @NotNull Commodity commodity = commodityService.require(param.getCommodityId());
        @NotNull User user = requireCurrentUser(userService);
        Order order = createOrder(user, commodity, param);
        Order res = orderService.create(order);
        return GeneralResult.ok(res);
    }

    /**
     * 创建一个携带基本信息的订单对象
     *
     * @param user      用户
     * @param commodity 商品
     * @param param     其他参数
     * @return 订单对象
     */
    private Order createOrder(User user, Commodity commodity, OrderCreateVo param) {
        Order order = new Order();
        order.setCommodityId(commodity.getId());
        order.setCommodity(commodity);
        order.setUserId(user.getId());
        order.setUser(user);
        order.setQuantity(param.getQuantity());
        order.setUsername(param.getUsername());
        order.setPhone(param.getPhone());
        //商铺ID 冗余存储
        order.setStoreId(commodity.getStoreId());
        return order;
    }
}
