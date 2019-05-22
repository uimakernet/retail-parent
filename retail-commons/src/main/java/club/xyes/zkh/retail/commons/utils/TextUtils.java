package club.xyes.zkh.retail.commons.utils;

import club.xyes.zkh.retail.commons.exception.BadRequestException;
import club.xyes.zkh.retail.commons.fn.GetterFunction;

/**
 * Create by 郭文梁 2019/4/20 0020 15:30
 * TextUtils
 * 文本相关工具类
 *
 * @author 郭文梁
 * @data 2019/4/20 0020
 */
public class TextUtils {
    /**
     * 去除两边空格后字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isTrimedEmpty(String str) {
        return str == null || "".equals(str) || "".equals(str.trim());
    }

    /**
     * 去除两边空格后字符串是否不为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isTrimedNotEmpty(String str) {
        return !(str == null || "".equals(str) || "".equals(str.trim()));
    }

    /**
     * 字符串非空检查
     *
     * @param getter Getter
     * @param errMsg 异常信息
     */
    public static void notEmpty(GetterFunction<String> getter, String errMsg) {
        if (isTrimedEmpty(getter.apply())) {
            throw new BadRequestException(errMsg);
        }
    }
}
