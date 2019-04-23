package com.winconlab.clams.controller;

import com.winconlab.clams.pojo.GlobalResult;
import com.winconlab.clams.pojo.User;
import com.winconlab.clams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser")
    public GlobalResult findAllUser() {
        return userService.findAllUser();
    }

    @RequestMapping("/register")
    public GlobalResult register(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.build(-1, bindingResult.getFieldError().getDefaultMessage());
        }
        return userService.register(user);
    }

    @RequestMapping("/login")
    public GlobalResult login(String username, String password) {
        return userService.login(username, password);
    }

    @RequestMapping("/changeUser")
    public GlobalResult changeUser(int userId, String newUsername, String newPassword, String userType) {
        if (StringUtils.isEmpty(userId)) {
            return GlobalResult.build(-1, "用户id不能为空");
        }
        return userService.update(userId, newUsername, newPassword, userType);
    }
}
