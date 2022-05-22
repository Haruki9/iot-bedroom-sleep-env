package com.xmu.haruki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class DashboardController {

    @GetMapping("dashboard")
    public ModelAndView dashboard(){
        return new ModelAndView("dashboard");
    }
}
