package club.xyes.zkh.retail.wechat.dto;

import lombok.Data;

/**
 * Create by 郭文梁 2019/5/28 0028 10:21
 * PrepayResult
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@Data
public class PrepayResult implements SourceJsonAware {
    private String sourceJson;
}
