package com.yys.szcp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
    @RequestMapping("/druid")
    public String druid(){
        return "redirect:/druid/index.html";
    }
}
