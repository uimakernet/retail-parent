package club.xyes.zkh.retail.service.general.impl;

import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.utils.RandomUtils;
import club.xyes.zkh.retail.repository.dao.mapper.UserMapper;
import club.xyes.zkh.retail.service.basic.impl.AbstractServiceImpl;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.wechat.api.Wechat;
import club.xyes.zkh.retail.wechat.dto.WxOAuth2AccessToken;
import club.xyes.zkh.retail.wechat.dto.WxUserInfo;
import club.xyes.zkh.retail.wechat.props.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private static final String LANG = "zh_CN";
    private final UserMapper userMapper;
    private final Wechat wechat;
    private final WechatConfig wechatConfig;

    @Autowired
    public UserServiceImpl(UserMapper mapper, Wechat wechat, WechatConfig wechatConfig) {
        super(mapper);
        this.userMapper = mapper;
        this.wechat = wechat;
        this.wechatConfig = wechatConfig;
    }

    @Override
    public User loginByOAuthCode(String code) {
        String appId = wechatConfig.getAppId();
        String secret = wechatConfig.getSecret();
        WxOAuth2AccessToken accessToken = wechat.code2Token(appId, secret, code);
        User user = findByOpenId(accessToken.getOpenId());
        if (user == null) {
            //说明用户第一次登陆
            User defaultUser = createDefaultUser(accessToken);
            user = save(defaultUser);
        }
        user = refreshUserInfo(user, accessToken);
        return user;
    }

    @Override
    public User findByOpenId(String openId) {
        User query = new User();
        query.setWxOpenId(openId);
        return findOneByQuery(query);
    }

    @Override
    public User refreshUserInfo(User user, WxOAuth2AccessToken accessToken) {
        WxUserInfo userInfo = wechat.getUserInfo(accessToken.getAccessToken(), user.getWxOpenId(), LANG);
        user.setNickname(userInfo.getNickname());
        user.setName(userInfo.getNickname());
        user.setWxAvatar(userInfo.getHeadimgurl());
        return updateById(user);
    }

    @Override
    public User findByPromoCode(String promoCode) {
        User query = new User();
        query.setPromoCode(promoCode);
        return findOneByQuery(query);
    }

    @Override
    public User toPromoter(User user) {
        String promoCode = randomPromoCode();
        user.setRole(User.ROLE_PROMOTERS);
        user.setPromoCode(promoCode);
        return updateById(user);
    }

    @Override
    public User toCaptain(User user) {
        String promoCode = randomPromoCode();
        user.setRole(User.ROLE_CAPTAIN);
        //默认为初级小队长
        user.setTeamHeaderLevel(User.LEVEL_PRIMARY);
        user.setPromoCode(promoCode);
        return updateById(user);
    }

    private User createDefaultUser(WxOAuth2AccessToken token) {
        User user = new User();
        user.setWxOpenId(token.getOpenId());
        user.setWxTokenJson(token.getSourceJson());
        user.setRole(User.ROLE_USER);
        return user;
    }

    /**
     * 生成随机推广码
     *
     * @return 推广码
     */
    private String randomPromoCode() {
        return RandomUtils.randomPrettyUUIDString();
    }

    @Override
    public List<User> searchByName(String name) {
        String query = String.format(SEARCH_TEMPLATE, name);
        return userMapper.searchByNameOrNickName(query);
    }
}
