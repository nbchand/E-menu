package com.ncit.emenu.controller;

import com.ncit.emenu.feature.PatternMatcher;
import com.ncit.emenu.model.User;
import com.ncit.emenu.service.UserSignupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignUpController {

    @Autowired
    UserSignupService userSignupService;

    @PostMapping("/signup")
    @ResponseBody
    public String signupUser(@RequestBody User user){

        System.out.println(user.getPassword());

    if(user.getEmail().equals("")||user.getFirstname().equals("")||user.getLastname().equals("")||user.getPassword().equals("")){
        return "Please fill all the necessary input fields";
    }
    
    if(!PatternMatcher.checkNamePattern(user.getFirstname())){
        return "First name is invalid";
    }

    if(!PatternMatcher.checkNamePattern(user.getLastname())){
        return "Last name is invalid";
    }

    if(!PatternMatcher.checkEmailPattern(user.getEmail())){
        return "Email address is invalid";
    }

    if(!PatternMatcher.checkPasswordPattern(user.getPassword())){
        return "Password invalid";

    }

    if(userSignupService.isEmailTaken(user.getEmail())){
        return "Account of this email already exists!";
    }

    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    userSignupService.userSignup(user);
    return "";
    }
}
