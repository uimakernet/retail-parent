package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.repository.dao.mapper.UserMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by 郭文梁 2019/5/18 0018 11:39
 * UserServiceImpl
 * 用户相关业务行为实现
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        super(mapper);
        this.userMapper = mapper;
    }
}
