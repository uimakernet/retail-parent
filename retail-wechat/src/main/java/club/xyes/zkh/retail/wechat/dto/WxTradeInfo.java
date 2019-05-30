package club.xyes.zkh.retail.wechat.dto;

import lombok.Data;

/**
 * Create by 郭文梁 2019/5/30 0030 10:23
 * WxTradeInfo
 * 微信交易记录查询结果
 *
 * @author 郭文梁
 * @data 2019/5/30 0030
 */
@Data
public class WxTradeInfo implements SourceJsonAware {
    private String sourceJson;

    /**
     * 获取是否已经成功支付
     *
     * @return 是否已经成功支付
     */
    public boolean isPaid() {
        return true;
    }
}
