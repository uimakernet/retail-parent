package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.CashApplication;
import club.xyes.zkh.retail.service.general.CashApplicationService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
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

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected CashApplicationController(CashApplicationService service) {
        super(service);
        this.cashApplicationService = service;
    }
}
