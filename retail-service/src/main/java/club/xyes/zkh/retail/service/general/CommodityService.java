package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.service.basic.AbstractService;

/**
 * Create by 郭文梁 2019/5/20 0020 11:15
 * CommodityService
 * 商品相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
public interface CommodityService extends AbstractService<Commodity> {
    /**
     * 通过ID查询商品 同时抓取出关联对象
     *
     * @param id ID
     * @return Commodity with all
     */
    Commodity requireFetchAll(Integer id);

    /**
     * 为商铺创建新的商品
     *
     * @param storeId   商铺ID
     * @param commodity 商品参数
     * @return GR
     */
    Commodity create(Integer storeId, Commodity commodity);
}
