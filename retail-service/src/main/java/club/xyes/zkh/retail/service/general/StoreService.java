package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.service.basic.AbstractService;

/**
 * Create by 郭文梁 2019/5/20 0020 11:13
 * StoreService
 * 商铺相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
public interface StoreService extends AbstractService<Store> {
    /**
     * 商家登录
     *
     * @param loginName 登录名
     * @param password  密码
     * @return Store
     */
    Store login(String loginName, String password);

    /**
     * 通过登录名查找商铺
     *
     * @param loginName 登录名
     * @return 商铺对象
     */
    Store findByLoginName(String loginName);
}
