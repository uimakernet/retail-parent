package club.xyes.zkh.retail.service.basic;

import club.xyes.zkh.retail.commons.entity.AbstractEntity;
import com.github.pagehelper.PageInfo;

import javax.validation.constraints.NotNull;
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

    /**
     * 分页查询所有对象
     *
     * @param page 页码
     * @param rows 每页大小
     * @return PageInfo With Entity
     */
    PageInfo<Entity> list(int page, int rows);

    /**
     * 通过ID更新数据
     *
     * @param o 实体对象
     * @return 更新后的结果
     */
    Entity updateById(Entity o);

    /**
     * 保存实体对象
     *
     * @param o 对象
     * @return 保存后的实体对象
     */
    Entity save(Entity o);

    /**
     * 保存所有数据
     *
     * @param os 列表
     * @return 保存结果
     */
    List<Entity> save(List<Entity> os);

    /**
     * 通过指定的模板条件查找
     *
     * @param query 模板条件
     * @return Entity list
     */
    List<Entity> findByQuery(Entity query);

    /**
     * 通过指定的条件模板查询一条数据
     *
     * @param query 条件模板
     * @return 实体对象
     */
    Entity findOneByQuery(Entity query);

    /**
     * 通过ID删除对象
     *
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * 获取总数量
     *
     * @return 数量
     */
    int count();

    /**
     * 获取符合条件的记录数量
     *
     * @param query 条件
     * @return 数量
     */
    int count(Entity query);

    /**
     * 根据条件检查数据是否存在
     *
     * @param query 条件
     * @return 是否存在
     */
    boolean exists(Entity query);

    /**
     * 通过制定条件删除数据
     *
     * @param query 条件
     * @return 删除的数据数量
     */
    int delete(Entity query);

    /**
     * 通过标记删除 并不是真的删除
     *
     * @param id ID
     * @return 删除结果
     */
    boolean removeById(Integer id);

    /**
     * 通过制定条件标记删除
     *
     * @param query 条件
     * @return 删除数量
     */
    int remove(Entity query);
}
