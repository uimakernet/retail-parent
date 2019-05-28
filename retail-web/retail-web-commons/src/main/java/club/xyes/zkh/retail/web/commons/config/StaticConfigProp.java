package club.xyes.zkh.retail.web.commons.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Create by 郭文梁 2019/5/20 0020 19:19
 * StaticConfigProp
 * 静态资源配置
 *
 * @author 郭文梁
 * @data 2019/5/20 0020
 */
@Data
@ConfigurationProperties(prefix = "site")
@Component
public class StaticConfigProp {
    /**
     * 静态资源服务器地址
     */
    private String staticServer;
    /**
     * 静态资源保存路径
     */
    private String staticFilePath;
    /**
     * 商品图片保存路径
     */
    private String commodityImagePath = "/commodity/image/";
}
