package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.CashApplication;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.CashApplicationService;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import club.xyes.zkh.retail.web.front.vo.WithdrawVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/25 0025 10:57
 * CashApplicationController
 * 提现申请相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/25 0025
 */
@RestController
@RequestMapping("/api/cash")
public class CashApplicationController extends AbstractEntityController<CashApplication> {
    private final CashApplicationService cashApplicationService;
    private final UserService userService;

    /**
     * 构造时指定业务组件
     *
     * @param service     业务组件
     * @param userService 用户业务组件
     */
    protected CashApplicationController(CashApplicationService service, UserService userService) {
        super(service);
        this.cashApplicationService = service;
        this.userService = userService;
    }

    @PostMapping("/create")
    public GeneralResult<CashApplication> create(@RequestBody WithdrawVo param) {
        if (param == null || param.getAmount() == null) {
            throw new BadRequestException("参数未传");
        }
        User user = requireCurrentUser(userService);
        CashApplication res = cashApplicationService.create(user, param.getAmount());
        return GeneralResult.ok(res);
    }
}
