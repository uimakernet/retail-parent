package club.xyes.zkh.retail.repository.dao.mapper;

import club.xyes.zkh.retail.commons.entity.Order;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/22 0022 16:38
 * OrderMapper
 * 订单相关数据库访问组件
 *
 * @author 郭文梁
 * @data 2019/5/22 0022
 */
@Repository
public interface OrderMapper extends AbstractMapper<Order> {
    /**
     * 通过用户ID查询订单记录
     *
     * @param userId 用户ID
     * @return Order List
     */
    List<Order> selectByUserId(@Param("userId") Integer userId);

    /**
     * 通过用户ID】和状态查询订单
     *
     * @param userId 用户ID
     * @param status 状态
     * @return OrderList
     */
    List<Order> selectByUserIdAndStatus(@Param("userId") Integer userId,
                                        @Param("status") Integer status);
}
