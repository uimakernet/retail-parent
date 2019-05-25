package club.xyes.zkh.retail.web.front.controller.open;

import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.commons.vo.GeneralResult;
import club.xyes.zkh.retail.service.encrypt.AccessTokenEncoder;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import club.xyes.zkh.retail.web.commons.vo.UserLoginCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * Create by 郭文梁 2019/5/24 0024 14:30
 * OpenUserController
 * 不带权限验证的用户数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/24 0024
 */
@RestController
@RequestMapping("/api/open/user")
public class OpenUserController extends AbstractEntityController<User> {
    private final UserService userService;
    private final AccessTokenEncoder accessTokenEncoder;

    /**
     * 构造时指定业务组件
     *
     * @param service 业务组件
     */
    protected OpenUserController(UserService service, AccessTokenEncoder accessTokenEncoder) {
        super(service);
        this.userService = service;
        this.accessTokenEncoder = accessTokenEncoder;
    }

    /**
     * 获取用户的登录令牌 仅供调试时使用
     *
     * @param id ID
     * @return GR with cookie
     */
    @GetMapping("/{id}/cookie")
    public GeneralResult<UserLoginCookie> cookie(@PathVariable("id") Integer id) {
        @NotNull User user = userService.require(id);
        UserLoginCookie cookie = new UserLoginCookie(user, accessTokenEncoder);
        return GeneralResult.ok(cookie);
    }

    /**
     * 获取用户的登录令牌 同时登录用户 仅供调试时使用
     *
     * @param id ID
     * @return GR with cookie
     */
    @GetMapping("/{id}/login")
    public GeneralResult<UserLoginCookie> login(@PathVariable("id") Integer id, HttpServletResponse response) {
        @NotNull User user = userService.require(id);
        UserLoginCookie cookie = new UserLoginCookie(user, accessTokenEncoder);
        cookie.write2Response(response);
        return GeneralResult.ok(cookie);
    }
}
