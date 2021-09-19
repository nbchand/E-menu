package com.ncit.emenu.service;

import com.ncit.emenu.model.User;
import com.ncit.emenu.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignupService {

    @Autowired
    UserRepo userRepo;

    public void userSignup(User user){
        userRepo.save(user);
    }

    public boolean isEmailTaken(String email){
        return userRepo.existsUserByEmail(email);
    }
}
