package com.ncit.emenu.controller;

import com.ncit.emenu.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class SignUpController {
    @PostMapping("/signup")
    public String signupUser(@RequestBody User user){
        System.out.println(user);
        return "successful";
    }
}
