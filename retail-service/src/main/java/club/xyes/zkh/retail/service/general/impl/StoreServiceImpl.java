package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.Store;
import club.xyes.zkh.retail.repository.dao.mapper.StoreMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
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
    private StoreMapper storeMapper;

    public StoreServiceImpl(StoreMapper mapper) {
        super(mapper);
        this.storeMapper = mapper;
    }
}
