package com.winconlab.clams.controller;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.pojo.SysPermission;
import com.winconlab.clams.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping("/addPermission")
    private String addPermission(SysPermission permission) {
        try {
            sysPermissionService.addPermission(permission);
        } catch (CustomMsgException e) {
            return e.getMessage();
        }
        return "success";
    }
}
