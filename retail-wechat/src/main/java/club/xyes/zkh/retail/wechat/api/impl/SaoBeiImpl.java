package club.xyes.zkh.retail.wechat.api.impl;

import club.xyes.zkh.retail.wechat.api.SaoBei;
import club.xyes.zkh.retail.wechat.dto.GoodsDetail;
import club.xyes.zkh.retail.wechat.dto.PrepayResult;
import club.xyes.zkh.retail.wechat.dto.SaoBeiPrepayParam;
import club.xyes.zkh.retail.wechat.props.SaoBeiConfig;
import org.springframework.stereotype.Component;

/**
 * Create by 郭文梁 2019/5/28 0028 10:30
 * SaoBeiImpl
 * 扫呗API实现
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@Component
public class SaoBeiImpl extends AbstractHttpApi implements SaoBei {
    private final SaoBeiConfig saoBeiConfig;

    public SaoBeiImpl(SaoBeiConfig saoBeiConfig) {
        this.saoBeiConfig = saoBeiConfig;
    }

    @Override
    public PrepayResult prepay(String openId, String orderBody, GoodsDetail goodsDetail, int totalFee, String tradeNo) {

        return null;
    }

    /**
     * 构建支付参数
     *
     * @param openId      openId
     * @param orderBody   订单描述
     * @param goodsDetail 商品详情
     * @param totalFee    支付金额
     * @return 支付参数
     */
    private SaoBeiPrepayParam buildParam(String openId, String orderBody, GoodsDetail goodsDetail, int totalFee) {
        SaoBeiPrepayParam param = new SaoBeiPrepayParam();
        param.setPayVar(saoBeiConfig.getPayVer());
        param.setPayType(SaoBeiConfig.PAY_TYPE_WECHAT);
        param.setServiceId(saoBeiConfig.getServiceId());
        param.setMerchantNo(saoBeiConfig.getMerchantNo());
        param.setTerminalId(saoBeiConfig.getTerminalId());
        return param;
    }
}
