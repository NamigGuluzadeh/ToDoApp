package com.myapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap modelMap) {
        modelMap.addAttribute("name",getLoggedInUser());
        return "welcome";
    }

    public String getLoggedInUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
