package club.xyes.zkh.retail.service.basic.impl;

import club.xyes.zkh.retail.commons.entity.AbstractEntity;
import club.xyes.zkh.retail.commons.exception.InternalServerErrorException;
import club.xyes.zkh.retail.commons.exception.ResourceNotFoundException;
import club.xyes.zkh.retail.repository.dao.AbstractMapper;
import club.xyes.zkh.retail.service.basic.AbstractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by 郭文梁 2019/5/18 0018 11:18
 * AbstractServiceImpl
 * 基础业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Service
@Slf4j
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

    @Override
    public PageInfo<Entity> list(int page, int rows) {
        return PageHelper.startPage(page, rows).doSelectPageInfo(mapper::selectAll);
    }

    @Override
    public Entity updateById(Entity o) {
        o.touch();
        int col = mapper.updateByPrimaryKey(o);
        if (col != 1) {
            throw new InternalServerErrorException("Unable to update entity:" + o);
        }
        return o;
    }

    @Override
    public Entity save(Entity o) {
        o.init();
        int col = mapper.insert(o);
        if (col != 1) {
            throw new InternalServerErrorException("Could not save entity:" + o);
        }
        return o;
    }

    @Override
    public List<Entity> save(List<Entity> os) {
        if (os == null || os.size() <= 0) {
            return null;
        }
        os = os.stream().peek(AbstractEntity::init).collect(Collectors.toList());
        int col = mapper.insertList(os);
        if (col == os.size()) {
            return os;
        } else {
            throw new InternalServerErrorException("Save error: length of expect to save is " +
                    os.size() + ", but Actual storage length is " + col + ", data: " + os);
        }
    }

    @Override
    public List<Entity> findByQuery(Entity query) {
        return mapper.select(query);
    }

    @Override
    public Entity findOneByQuery(Entity query) {
        return mapper.selectOne(query);
    }

    @Override
    public boolean deleteById(Integer id) {
        int col = mapper.deleteByPrimaryKey(id);
        log.debug("Delete {} where id={}", entityClass, id);
        if (col > 1) {
            throw new InternalServerErrorException("Delete Error: result is " + col);
        }
        return col == 1;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(Entity query) {
        return mapper.selectCount(query);
    }

    @Override
    public boolean exists(Entity query) {
        return count(query) > 0;
    }


    @Override
    public int delete(Entity query) {
        return mapper.delete(query);
    }

    @Override
    public boolean removeById(Integer id) {
        @NotNull Entity entity = require(id);
        entity.setDeleted(true);
        Entity res = updateById(entity);
        return res != null;
    }

    @Override
    public int remove(Entity query) {
        return (int) findByQuery(query)
                .stream()
                .peek(e -> e.setDeleted(true))
                .filter(e -> updateById(e) != null)
                .count();
    }
}
