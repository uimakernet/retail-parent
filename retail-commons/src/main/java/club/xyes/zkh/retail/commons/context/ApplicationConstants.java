package club.xyes.zkh.retail.commons.context;

import java.time.ZoneId;

/**
 * Create by 郭文梁 2019/4/15 0015 15:03
 * ApplicationConstants
 * 系统常量
 *
 * @author 郭文梁
 * @data 2019/4/15 0015
 */
public class ApplicationConstants {
    /**
     * 默认时区
     */
    public static final ZoneId DEFAULT_TIMEZONE = ZoneId.systemDefault();
    /**
     * 系统默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * 默认时间如期格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    public static final String TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 图片分隔符
     */
    public static final String IMAGE_DELIMITER = ",";

    /**
     * 上下文 系统常量
     */
    public static class Context {
        /**
         * Mapper组建所在的包名
         */
        public static final String MAPPER_PACKAGE = "club.xyes.zkh.retail.repository.dao.mapper";
        /**
         * 控制器(API)所在的包
         */
        public static final String API_CONTROLLER_PACKAGE = "club.xyes.zkh.retail.web";
        /**
         * 组件所在的基础包
         */
        public static final String COMPONENT_PACKAGE = "club.xyes.zkh.retail";
    }

    public static class Security {
    }

    /**
     * 数据库相关常量
     */
    public static class Database {
        /**
         * JDBC主键生成器名称
         */
        public static final String GENERATOR_JDBC = "JDBC";
        /**
         * 降序排序
         */
        public static final String ORDER_DESC = "desc";
        /**
         * 升序排序
         */
        public static final String ORDER_ASC = "asc";
    }

    /**
     * Http相关常量
     */
    public static class Http {
        /**
         * 内容类型 JSON 编码 UTF-8
         */
        public static final String CONTENT_TYPE_JSON_UTF8 = "application/json;charset=utf-8";
        /**
         * API路径
         */
        public static final String API_BASE_PATH = "/api/**";
        /**
         * 开放授权Api地址
         */
        public static final String WITHOUT_AUTH_PATH = "/api/open/**";
        /**
         * 用户附加参数名称
         */
        public static final String USER_TOKEN_EXTEND_PARAM_NAME = "userToken";
    }
}
