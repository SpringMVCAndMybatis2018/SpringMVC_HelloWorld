package com.anlu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String helloworld(ModelMap model){
        model.addAttribute("msg", "Hello Spring MVC Framework!");
        return "hello";
    }
}
