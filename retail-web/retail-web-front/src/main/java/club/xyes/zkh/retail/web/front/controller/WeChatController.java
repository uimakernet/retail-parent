package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.web.commons.controller.AbstractController;
import club.xyes.zkh.retail.wechat.api.Wechat;
import club.xyes.zkh.retail.wechat.dto.WxJsApiConfigParam;
import club.xyes.zkh.retail.wechat.props.WechatConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by 郭文梁 2019/5/18 0018 15:33
 * WeChatController
 * 微信相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@RestController
@RequestMapping("/api/wx")
public class WeChatController extends AbstractController {
    private final Wechat wechat;
    private final WechatConfig wechatConfig;

    public WeChatController(Wechat wechat, WechatConfig wechatConfig) {
        this.wechat = wechat;
        this.wechatConfig = wechatConfig;
    }

    /**
     * 获取微信JsApi配置参数
     *
     * @param url 地址
     * @return GR
     */
    @GetMapping("/jsapi-config")
    public GeneralResult<WxJsApiConfigParam> configParam(@RequestParam("url") String url) {
        String appId = wechatConfig.getAppId();
        String secret = wechatConfig.getSecret();
        WxJsApiConfigParam config = wechat.getJsApiConfigParam(appId, secret, url);
        return GeneralResult.ok(config);
    }
}
