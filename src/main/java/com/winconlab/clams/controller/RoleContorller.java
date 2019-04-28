package com.winconlab.clams.controller;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.pojo.SysRole;
import com.winconlab.clams.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleContorller {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/addRole")
    private String addRole(SysRole sysRole) {
        try {
            sysRoleService.addRole(sysRole);
        } catch (SysUserException e) {
            return e.getMessage();
        }
        return "success";
    }
}
