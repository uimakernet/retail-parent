package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.service.encrypt.AccessTokenEncoder;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.web.commons.controller.AbstractController;
import club.xyes.zkh.retail.web.commons.vo.UserLoginCookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Create by 郭文梁 2019/5/18 0018 17:02
 * WxUserController
 * 微信用户相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@Controller
@RequestMapping("/wx-user")
@Slf4j
public class WxUserController extends AbstractController {
    private final UserService userService;
    private final AccessTokenEncoder accessTokenEncoder;

    public WxUserController(UserService userService,
                            AccessTokenEncoder accessTokenEncoder) {
        this.userService = userService;
        this.accessTokenEncoder = accessTokenEncoder;
    }

    /**
     * 微信网页授权登录
     * 首先引导用户打开微信授权链接（比如https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx45725d38c171c33f&redirect_uri=http%3A%2F%2Fwz.jinguanjiazhifu.com%2Fretail%2F&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect）
     * 微信将重定向到该地址
     *
     * @param code     code
     * @param state    state
     * @param response 响应对象
     * @return MV
     */
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("code") String code,
                              @RequestParam("state") String state,
                              HttpServletResponse response) {
        log.debug("Login by code[{}], state[{}]", code, state);
        User user = userService.loginByOAuthCode(code);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", user);
        setLoginCookie(user, response);
        return mv;
    }

    /**
     * 设置登录Token Cookie
     *
     * @param user     用户
     * @param response 响应对象
     */
    private void setLoginCookie(User user, HttpServletResponse response) {
        UserLoginCookie cookie = new UserLoginCookie(user, accessTokenEncoder);
        cookie.write2Response(response);
    }
}
