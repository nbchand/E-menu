package com.ncit.emenu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @Autowired
    ItemService itemService;
    
    @GetMapping("/menu")
    public String displayHome(Model model, HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/";
        }

        List<Item> items = itemService.getUserItems((int)session.getAttribute("userId"));
        model.addAttribute("items", items);
        return "menu";
    }

}
