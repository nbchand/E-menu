package com.ncit.emenu.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ncit.emenu.model.User;
import com.ncit.emenu.service.UserLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/login")
    @ResponseBody
    public String loginUser(@RequestBody Map<String,String> json, HttpSession session){
    
        User u = userLoginService.loginUser(json.get("email"));
    
        if (u != null) {
            if(!DigestUtils.md5DigestAsHex(json.get("password").getBytes()).equals(u.getPassword())){
                return "Invalid password for the provided email";
            }
            session.setAttribute("userId", u.getUserId());
            session.setMaxInactiveInterval(1000);
            return "";
        }
            return "Account doesnot exist";
    }

}