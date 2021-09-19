package com.ncit.emenu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    
    @GetMapping("/menu")
    public String displayHome(HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/";
        }
        return "menu";
    }

}
