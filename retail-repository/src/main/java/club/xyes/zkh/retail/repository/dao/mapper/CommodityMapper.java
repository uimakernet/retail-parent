package club.xyes.zkh.retail.repository.dao.mapper;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/20 0020 11:08
 * CommodityMapper
 * 商品相关数据库访问组件
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Repository
public interface CommodityMapper extends AbstractMapper<Commodity> {
    /**
     * 查询可用的商品，同时以创建时间降序排序
     *
     * @return Commodity List
     */
    List<Commodity> selectAvailableOrderByCreateTimeDesc();

    /**
     * 通过ID查询商铺 同时抓取出关联对象
     *
     * @param id ID
     * @return Commodity
     */
    Commodity selectByIdFetchAll(@Param("id") Integer id);
}
