package club.xyes.zkh.retail.service.basic;

import club.xyes.zkh.retail.commons.entity.AbstractEntity;
import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/18 0018 11:15
 * AbstractService
 * 基础业务服务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
public interface AbstractService<Entity extends AbstractEntity> {
    /**
     * 根据ID查询实体对象
     * 可能为空
     *
     * @param id ID
     * @return 实体对象
     */
    Entity get(Integer id);

    /**
     * 根据ID查询实体对象
     * 不存在时抛出异常
     *
     * @param id ID
     * @return 实体对象
     */
    @NotNull
    Entity require(Integer id);

    /**
     * 获取全部对象
     *
     * @return 实体对象列表
     */
    List<Entity> all();
}
