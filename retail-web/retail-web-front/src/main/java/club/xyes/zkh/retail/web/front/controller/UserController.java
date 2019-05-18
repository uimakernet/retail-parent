package club.xyes.zkh.retail.web.front.controller;

import club.xyes.zkh.retail.commons.context.ApplicationConstants;
import club.xyes.zkh.retail.commons.entity.User;
import club.xyes.zkh.retail.service.general.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> all() {
        return userService.all();
    }
}
