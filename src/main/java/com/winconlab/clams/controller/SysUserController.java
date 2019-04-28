package com.winconlab.clams.controller;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.pojo.SysUser;
import com.winconlab.clams.service.SysPermissionService;
import com.winconlab.clams.service.SysRoleService;
import com.winconlab.clams.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;

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
    private ModelAndView login(ModelAndView modelAndView, HttpServletRequest request, SysUser user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            String error_msg = e.getMessage();
            if (error_msg.contains("did not match the expected credentials"))
                error_msg = "账号/密码错误";
            modelAndView.addObject("error_msg", error_msg);
            modelAndView.setViewName("/login");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/page/index");
        return modelAndView;
    }

    @RequestMapping("/logout")
    private String logout() {
        return "redirect:/page/login";
    }

    @RequestMapping("/addRole")
    @ResponseBody
    private String addRole(String userid, String roles_id) {
        try {
            sysRoleService.addRolesToUser(userid, roles_id);
        } catch (SysUserException e) {
            return e.getMessage();
        }

        return "success";
    }

    @RequestMapping("/addPermission")
    @ResponseBody
    private String addPermission(String userid, String permissions_id) {
        try {
            sysPermissionService.addPermissionsToRole(userid, permissions_id);
        } catch (SysUserException e) {
            return e.getMessage();
        }
        return "success";
    }

}
