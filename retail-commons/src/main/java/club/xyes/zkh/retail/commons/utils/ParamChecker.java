package club.xyes.zkh.retail.commons.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Create by 郭文梁 2019/5/27 0027 15:11
 * ParamChecker
 * 参数检查相关工具
 *
 * @author 郭文梁
 * @data 2019/5/27 0027
 */
public class ParamChecker {
    /**
     * 检查参数是否为空 为空则抛出异常
     *
     * @param o              参数
     * @param exceptionClass 异常类
     * @param msg            附加信息
     */
    public static void notNull(Object o, Class<? extends RuntimeException> exceptionClass, String msg) {
        if (o == null) {
            try {
                Constructor<? extends RuntimeException> constructor = exceptionClass.getConstructor(String.class);
                throw constructor.newInstance(msg);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
