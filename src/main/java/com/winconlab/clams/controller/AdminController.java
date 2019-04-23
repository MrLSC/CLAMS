package com.winconlab.clams.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @RequestMapping("/show")
    public String show(){
        return "欢迎来访!";
    }

}
