package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.Commodity;
import club.xyes.zkh.retail.commons.exception.ResourceNotFoundException;
import club.xyes.zkh.retail.repository.dao.mapper.CommodityMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.CommodityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * Create by 郭文梁 2019/5/20 0020 11:16
 * CommodityServiceImpl
 * 商品相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Service
public class CommodityServiceImpl extends AbstractServiceImpl<Commodity> implements CommodityService {
    private CommodityMapper commodityMapper;

    public CommodityServiceImpl(CommodityMapper mapper) {
        super(mapper);
        this.commodityMapper = mapper;
    }

    @Override
    public PageInfo<Commodity> list(int page, int rows) {
        return PageHelper
                .startPage(page, rows)
                .doSelectPageInfo(() -> commodityMapper.selectAvailableOrderByCreateTimeDesc());
    }

    @Override
    public Commodity requireFetchAll(Integer id) {
        Commodity commodity = commodityMapper.selectByIdFetchAll(id);
        if (commodity == null) {
            throw new ResourceNotFoundException(Commodity.class, id);
        }
        return commodity;
    }
}
