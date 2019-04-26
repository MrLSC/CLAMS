package com.winconlab.clams.controller;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.pojo.SysUser;
import com.winconlab.clams.service.SysUserService;
import com.winconlab.clams.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/register")
    private String register() {

        SysUser sysUser = new SysUser();
        sysUser.setUsername("zhangsanfeng");
        sysUser.setPassword("123456");

        try {
            sysUserService.addSysUser(sysUser);
        } catch (SysUserException e) {
            return e.getMessage();
        }

        return "注册成功";
    }

    @RequestMapping("/login")
    private String login(SysUser user) {
//        SysUser sysUser = sysUserService.findSysUserByUsername(user.getUsername());
//        if (sysUser == null)
//            return "/login";
//        else {
//            if (UserUtil.verifyUserPassword(user.getPassword(), sysUser.getSalt(), sysUser.getPassword())) {
//                return "/index";
//            } else return "/notAuth";
//        }


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return "/notAuth";
        }

        return "/index";
    }

}
