package club.xyes.zkh.retail.wechat.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Create by 郭文梁 2019/5/28 0028 10:54
 * SaoBeiPrepayParam
 * 扫呗预支付参数封装对象
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@Data
public class SaoBeiPrepayParam {
    @JSONField(name = "pay_ver")
    private String payVar;
    @JSONField(name = "pay_type")
    private String payType;
    @JSONField(name = "service_id")
    private String serviceId;
    @JSONField(name = "merchant_no")
    private String merchantNo;
    @JSONField(name = "terminal_id")
    private String terminalId;
    @JSONField(name = "terminal_trace")
    private String terminalTrace;
    @JSONField(name = "terminal_time")
    private String terminalTime;
    @JSONField(name = "total_fee")
    private String totalFee;
    @JSONField(name = "sub_appid")
    private String subAppId;
    @JSONField(name = "open_id")
    private String openId;
    @JSONField(name = "order_body")
    private String orderBody;
    @JSONField(name = "notify_url")
    private String notifyUrl;
    private String attach;
    @JSONField(name = "goods_detail")
    private String goodsDetail;
    @JSONField(name = "key_sign")
    private String keySign;
}
