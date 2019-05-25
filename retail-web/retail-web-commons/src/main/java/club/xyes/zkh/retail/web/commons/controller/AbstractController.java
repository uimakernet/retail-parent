package club.xyes.zkh.retail.web.commons.controller;

import club.xyes.zkh.retail.commons.context.ApplicationConstants;
import club.xyes.zkh.retail.commons.exception.PermissionDeniedException;
import club.xyes.zkh.retail.commons.holder.RequestExtendParamHolder;
import club.xyes.zkh.retail.web.commons.vo.UserLoginCookie;

import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/18 0018 12:38
 * AbstractController
 * 控制器基类
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
public abstract class AbstractController {
    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE = 1;
    /**
     * 默认每页大小
     */
    public static final int DEFAULT_ROWS = 10;

    /**
     * 当传入的page=null时返回默认页码，否则返回page
     *
     * @param page 页码
     * @return 页码
     */
    @NotNull
    protected int defaultPage(Integer page) {
        return page == null ? DEFAULT_PAGE : page;
    }

    /**
     * 当传入的rows==null时返回默认每页大小，否则反回rows
     *
     * @param rows 每页大小
     * @return 每页大小
     */
    @NotNull
    protected int defaultRows(Integer rows) {
        return rows == null ? DEFAULT_ROWS : rows;
    }

    /**
     * 获取当前用户登录Cookie
     *
     * @return 用户登录Cookie
     */
    UserLoginCookie getUserLoginCookie() {
        return RequestExtendParamHolder.get(ApplicationConstants.Http.USER_TOKEN_EXTEND_PARAM_NAME, UserLoginCookie.class);
    }

    /**
     * 获取用户登录Cookie 若为空则抛出异常
     *
     * @return UserLoginCookie
     */
    UserLoginCookie requireUserLoginCookie() {
        UserLoginCookie userLoginCookie = getUserLoginCookie();
        if (userLoginCookie == null) {
            throw new PermissionDeniedException("当前未登录任何用户");
        }
        return userLoginCookie;
    }

    /**
     * 获取用户基本信息 若不存在 则抛出异常
     *
     * @return 用户基本信息
     */
    public UserLoginCookie.UserInfo requireUserInfo() {
        return requireUserLoginCookie().getUserInfo();
    }
}
