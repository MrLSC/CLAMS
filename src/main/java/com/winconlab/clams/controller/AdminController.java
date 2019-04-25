package com.winconlab.clams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "欢迎来访!";
    }

}
