package com.ra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {
    @GetMapping("")
    public String home(){
        return "home";
    }
}
