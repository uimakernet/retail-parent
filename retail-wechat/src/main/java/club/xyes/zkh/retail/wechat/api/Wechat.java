package club.xyes.zkh.retail.wechat.api;

import club.xyes.zkh.retail.wechat.dto.*;

/**
 * Create by 郭文梁 2019/5/18 0018 13:46
 * Wechat
 * 微信Api
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
public interface Wechat {
    /**
     * 我去微信AccessToken
     *
     * @param appId  AppId
     * @param secret Secret
     * @return WxAccessToken
     */
    WxAccessToken getToken(String appId, String secret);

    /**
     * 获取JsApiTicket
     *
     * @param appId  AppId
     * @param secret Secret
     * @return WxJsApiTicket
     */
    WxJsApiTicket getJsApiTicket(String appId, String secret);

    /**
     * 获取JsApi配置参数
     *
     * @param appId  AppId
     * @param secret Secret
     * @param url    Url
     * @return WxJsApiConfigParam
     */
    WxJsApiConfigParam getJsApiConfigParam(String appId, String secret, String url);

    /**
     * 用Code换取AccessToken和OpenId
     *
     * @param appId  AppId
     * @param secret Secret
     * @param code   code
     * @return WxOAuth2AccessToken
     */
    WxOAuth2AccessToken code2Token(String appId, String secret, String code);

    /**
     * 获取用户信息
     *
     * @param accessToken AccessToken
     * @param openId      OpenId
     * @param lang        语言
     * @return 微信用户信息
     */
    WxUserInfo getUserInfo(String accessToken, String openId, String lang);
}
