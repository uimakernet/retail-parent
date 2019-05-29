package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.service.basic.AbstractService;
import com.github.pagehelper.PageInfo;

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

    /**
     * 通过商铺ID查询商品
     *
     * @param storeId 商铺ID
     * @param page    页码
     * @param rows    每页大小
     * @return Commodity PageInfo
     */
    PageInfo<Commodity> findByStoreId(Integer storeId, int page, int rows);
}
