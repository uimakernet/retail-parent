package club.xyes.zkh.retail.wechat.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Create by 郭文梁 2019/5/18 0018 15:35
 * WechatConfig
 * 微信相关配置参数
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class WechatConfig {
    /**
     * 微信公众号AppId
     */
    private String appId;
    /**
     * 微信公众号Secret
     */
    private String secret;
}
