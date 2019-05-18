package club.xyes.zkh.retail.service.general;

import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.service.basic.AbstractService;
import club.xyes.zkh.retail.wechat.dto.WxOAuth2AccessToken;

/**
 * Create by 郭文梁 2019/5/18 0018 11:37
 * UserService
 * 用户相关业务行为定义
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
public interface UserService extends AbstractService<User> {
    /**
     * 通过web oauth2 code登录
     *
     * @param code code
     * @return 用户
     */
    User loginByOAuthCode(String code);

    /**
     * 通过微信OpenId查找用户
     *
     * @param openId OpenId
     * @return 用户
     */
    User findByOpenId(String openId);

    /**
     * 从微信刷新用户信息
     *
     * @param user 用户对象
     * @return 刷新结果
     */
    User refreshUserInfo(User user, WxOAuth2AccessToken accessToken);
}
