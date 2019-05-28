package club.xyes.zkh.retail.wechat.api;

import club.xyes.zkh.retail.wechat.dto.GoodsDetail;
import club.xyes.zkh.retail.wechat.dto.PrepayResult;

/**
 * Create by 郭文梁 2019/5/28 0028 10:13
 * SaoBei
 * 扫呗API
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
public interface SaoBei {
    /**
     * 公总号预支付
     *
     * @param openId      用户openID
     * @param orderBody   订单描述
     * @param goodsDetail 商品详情
     * @param totalFee    支付金额
     * @param tradeNo     订单号
     * @return 预支付请求结果
     */
    PrepayResult prepay(String openId, String orderBody, GoodsDetail goodsDetail, int totalFee, String tradeNo);
}
