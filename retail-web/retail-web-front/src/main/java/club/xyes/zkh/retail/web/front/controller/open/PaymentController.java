package club.xyes.zkh.retail.web.front.controller.open;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by 郭文梁 2019/5/28 0028 10:41
 * PaymentController
 * 支付相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/28 0028
 */
@RestController
@RequestMapping("/pay")
@Slf4j
public class PaymentController {
    /**
     * 处理支付结果通知
     *
     * @param notifyData 通知数据
     * @return 响应数据
     */
    @RequestMapping("/notify")
    public Map<String, String> onNotify(@RequestBody String notifyData) {
        log.debug("On payment notify [{}]", notifyData);
        return buildNotifySuccessResult();
    }

    /**
     * 构建通知成功的返回结果
     *
     * @return 响应结果
     */
    private Map<String, String> buildNotifySuccessResult() {
        Map<String, String> res = new HashMap<>(2);
        res.put("return_code", "01");
        res.put("return_msg", "OK");
        return res;
    }
}
