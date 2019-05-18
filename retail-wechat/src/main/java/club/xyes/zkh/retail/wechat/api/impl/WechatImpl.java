package club.xyes.zkh.retail.wechat.api.impl;

import club.xyes.zkh.retail.commons.utils.RandomUtils;
import club.xyes.zkh.retail.wechat.api.Wechat;
import club.xyes.zkh.retail.wechat.dto.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by 郭文梁 2019/5/18 0018 13:47
 * WechatImpl
 * 微信Api实现
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Component
public class WechatImpl extends AbstractHttpApi implements Wechat {
    /**
     * 获取AccessToken
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 获取JsApiTicket
     */
    private static final String JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    /**
     * OAuth2 code换Token的Api地址
     */
    private static final String CODE_2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    /**
     * 获取用户信息的Api地址
     */
    private static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s";
    private static final int NONCE_STR_LEN = 15;
    /**
     * AccessToken缓存
     */
    private Map<String, WxAccessToken> localAccessTokenCache = new ConcurrentHashMap<>();
    /**
     * JsApiTicket缓存
     */
    private Map<String, WxJsApiTicket> localJsApiTicketCache = new ConcurrentHashMap<>();

    @Override
    public WxAccessToken getToken(String appId, String secret) {
        WxAccessToken lastToken = localAccessTokenCache.get(appId);
        if (lastToken != null && !lastToken.expired()) {
            return lastToken;
        }
        String tokenUrl = String.format(ACCESS_TOKEN_URL, appId, secret);
        lastToken = get(tokenUrl, WxAccessToken.class, res -> res.get("errcode") == null);
        lastToken.setRefreshTime(System.currentTimeMillis() / 1000);
        localAccessTokenCache.put(appId, lastToken);
        return lastToken;
    }

    @Override
    public WxJsApiTicket getJsApiTicket(String appId, String secret) {
        WxJsApiTicket ticket = localJsApiTicketCache.get(appId);
        if (ticket != null && !ticket.expired()) {
            return ticket;
        }
        WxAccessToken token = getToken(appId, secret);
        String apiUrl = String.format(JS_API_TICKET_URL, token.getAccessToken());
        ticket = get(apiUrl, WxJsApiTicket.class, res -> res.getInteger("errcode") == 0);
        ticket.setRefreshTime(System.currentTimeMillis() / 1000);
        localJsApiTicketCache.put(appId, ticket);
        return ticket;
    }

    @Override
    public WxJsApiConfigParam getJsApiConfigParam(String appId, String secret, String url) {
        String nonceStr = RandomUtils.randomString(NONCE_STR_LEN, RandomUtils.ALL_VISIBLE_CHARS);
        WxJsApiTicket ticket = getJsApiTicket(appId, secret);
        long timestamp = System.currentTimeMillis() / 1000;
        String signTemplate = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
        String signText = String.format(signTemplate, ticket.getTicket(), nonceStr, timestamp, url);
        String sign = DigestUtils.sha1Hex(signText);

        WxJsApiConfigParam res = new WxJsApiConfigParam();
        res.setAppId(appId);
        res.setNonceStr(nonceStr);
        res.setSignature(sign);
        res.setTimestamp(timestamp);
        return res;
    }

    @Override
    public WxOAuth2AccessToken code2Token(String appId, String secret, String code) {
        String apiUrl = String.format(CODE_2_TOKEN_URL, appId, secret, code);
        return get(apiUrl, WxOAuth2AccessToken.class, res -> res.get("openid") != null && res.get("access_token") != null);
    }

    @Override
    public WxUserInfo getUserInfo(String accessToken, String openId, String lang) {
        String apiUrl = String.format(GET_USER_INFO_URL, accessToken, openId, lang);
        return get(apiUrl, WxUserInfo.class, res -> res.get("errcode") == null);
    }
}
