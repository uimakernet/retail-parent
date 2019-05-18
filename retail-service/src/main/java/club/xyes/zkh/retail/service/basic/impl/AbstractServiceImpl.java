package club.xyes.zkh.retail.service.basic.impl;

import club.xyes.zkh.retail.commons.entity.AbstractEntity;
import club.xyes.zkh.retail.commons.exception.ResourceNotFoundException;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import club.xyes.zkh.retail.service.basic.AbstractService;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Create by 郭文梁 2019/5/18 0018 11:18
 * AbstractServiceImpl
 * 基础业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Service
public abstract class AbstractServiceImpl<Entity extends AbstractEntity> implements AbstractService<Entity> {
    private final AbstractMapper<Entity> mapper;
    private final Class<? extends AbstractEntity> entityClass;

    public AbstractServiceImpl(AbstractMapper<Entity> mapper) {
        this.mapper = mapper;
        entityClass = obtainEntityClass();
    }

    /**
     * 获取实际的泛型类型
     *
     * @return 泛型类型
     */
    @SuppressWarnings("unchecked")
    private Class<? extends AbstractEntity> obtainEntityClass() {
        Type superclass = getClass().getGenericSuperclass();
        while (!(superclass instanceof ParameterizedType)) {
            if (superclass instanceof Class) {
                Class<?> clazz = (Class<?>) superclass;
                superclass = clazz.getGenericSuperclass();
            } else {
                throw new IllegalArgumentException("The superclass of service must be a Adapter or BaseServiceImpl");
            }
        }
        ParameterizedType type = (ParameterizedType) superclass;
        Type typeArgument = type.getActualTypeArguments()[0];
        return (Class<? extends AbstractEntity>) typeArgument;
    }

    @Override
    public Entity get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @NotNull
    public Entity require(Integer id) {
        Entity entity = get(id);
        if (entity == null) {
            throw new ResourceNotFoundException(entityClass, id);
        }
        return entity;
    }

    @Override
    public List<Entity> all() {
        return mapper.selectAll();
    }
}
