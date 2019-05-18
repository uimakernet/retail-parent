package club.xyes.zkh.retail.wechat.api.impl;

import club.xyes.zkh.retail.commons.exception.InternalServerErrorException;
import club.xyes.zkh.retail.wechat.dto.SourceJsonAware;
import club.xyes.zkh.retail.wechat.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Create by 郭文梁 2019/5/18 0018 13:46
 * AbstractHttpApi
 * Http方式的Api基类
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
abstract class AbstractHttpApi {
    /**
     * 发送Get请求
     *
     * @param url Url
     * @return response string
     */
    String get(String url) {
        try {
            return HttpUtils.get(url);
        } catch (IOException e) {
            throw new InternalServerErrorException("Http get[" + url + "]", e);
        }
    }

    /**
     * 发送Get请求，并将接管反序列化（json）
     *
     * @param url           请求地址
     * @param responseClass class
     * @param <T>           类型
     * @return 反序列化结果
     */
    <T> T get(String url, Class<T> responseClass) {
        String jsonString = get(url);
        T res = JSON.parseObject(jsonString, responseClass);
        if (res instanceof SourceJsonAware) {
            ((SourceJsonAware) res).setSourceJson(jsonString);
        }
        return res;
    }

    /**
     * 方get请求，并将响应结果反序列化，
     * 当检查器返回false时抛出InternalServerErrorException异常
     *
     * @param url           Api地址
     * @param responseClass 响应结果class
     * @param checker       检查器
     * @param <T>           反序列化类型
     * @return T
     */
    <T> T get(String url, Class<T> responseClass, JsonResponseChecker checker) {
        String response = get(url);
        JSONObject jsonObject = JSON.parseObject(response);
        if (checker.isSuccess(jsonObject)) {
            T res = jsonObject.toJavaObject(responseClass);
            if (res instanceof SourceJsonAware) {
                ((SourceJsonAware) res).setSourceJson(response);
            }
            return res;
        }
        throw new InternalServerErrorException("Http get[" + url + "]:response [" + response + "]is unexpect!");
    }

    /**
     * 响应检查器
     */
    @FunctionalInterface
    public interface JsonResponseChecker {
        /**
         * 检查接口调用是否成功
         *
         * @param response 响应数据
         * @return 是否成功
         */
        boolean isSuccess(JSONObject response);
    }
}
