package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.StoreService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/20 0020 14:08
 * StoreController
 * 商铺相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@RestController
@RequestMapping("/api/store")
public class StoreController extends AbstractEntityController<Store> {
    private final StoreService storeService;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected StoreController(StoreService service) {
        super(service);
        this.storeService = service;
    }

    /**
     * 获取商铺的详细信息
     *
     * @param id ID
     * @return GR with store
     */
    @GetMapping("/{id}")
    public GeneralResult<Store> detail(@PathVariable("id") Integer id) {
        @NotNull Store store = storeService.require(id);
        return GeneralResult.ok(store);
    }
}
