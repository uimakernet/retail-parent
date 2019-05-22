package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.repository.dao.mapper.StoreMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.encrypt.PasswordEncryptor;
import club.xyes.zkh.retail.service.general.StoreService;
import org.springframework.stereotype.Service;

/**
 * Create by 郭文梁 2019/5/20 0020 11:13
 * StoreServiceImpl
 * 商铺相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Service
public class StoreServiceImpl extends AbstractServiceImpl<Store> implements StoreService {
    private final StoreMapper storeMapper;
    private final PasswordEncryptor passwordEncryptor;

    public StoreServiceImpl(StoreMapper mapper, PasswordEncryptor passwordEncryptor) {
        super(mapper);
        this.storeMapper = mapper;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public Store login(String loginName, String password) {
        Store store = findByLoginName(loginName);
        if (store != null && passwordEncryptor.matches(store.getPassword(), password)) {
            return store;
        }
        throw new BadRequestException("用户名或密码错误");
    }

    @Override
    public Store findByLoginName(String loginName) {
        Store query = new Store();
        query.setLoginName(loginName);
        return findOneByQuery(query);
    }
}
