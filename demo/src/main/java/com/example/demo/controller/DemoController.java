package com.example.demo.controller;

import com.example.demo.consts.UrlPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping(UrlPath.HOME_PAGE)
    public String showHome() {
        return "dashboard";
    }

}









