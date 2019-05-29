package club.xyes.zkh.retail.web.backstage.controller;

import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.general.StoreService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/29 0029 13:03
 * StoreController
 * 商铺相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/29 0029
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
     * 分页查询所有商铺
     *
     * @param page 页码
     * @param rows 每页大小
     * @return GR with PageInfo
     */
    @GetMapping("/all")
    public GeneralResult<PageInfo<Store>> all(Integer page,
                                              Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        PageInfo<Store> storePageInfo = storeService.list(page, rows);
        return GeneralResult.ok(storePageInfo);
    }
}
