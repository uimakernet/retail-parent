package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.context.ApplicationConstants;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.service.general.UserService;
import club.xyes.zkh.retail.web.commons.controller.AbstractEntityController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * Create by 郭文梁 2019/5/18 0018 11:35
 * UserController
 * 用户相关数据访问控制器
 *
 * @author 郭文梁
 * @data 2019/5/18 0018
 */
@RestController
@RequestMapping("/api/user")
@MapperScan(ApplicationConstants.Context.MAPPER_PACKAGE)
@Slf4j
public class UserController extends AbstractEntityController<User> {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> all() {
        return userService.all();
    }
}
