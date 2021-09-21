package com.ncit.emenu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    
    @GetMapping("/logout")
    public String logoutUser(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
