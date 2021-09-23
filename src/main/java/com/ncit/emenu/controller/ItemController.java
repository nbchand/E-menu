package com.ncit.emenu.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.ncit.emenu.exception.StorageException;
import com.ncit.emenu.feature.PatternMatcher;
import com.ncit.emenu.model.Item;
import com.ncit.emenu.service.ItemService;
import com.ncit.emenu.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    @Autowired
    UploadService uploadService;

    @Autowired
    ItemService itemService;

    @PostMapping("/create-item")
    public String saveItems(@RequestParam("img") MultipartFile multipartFile, @RequestParam("name") String name,
    @RequestParam("price") String price, HttpSession session, RedirectAttributes redirectAttributes){

        Item item = new Item();

        if(!PatternMatcher.checkItemName(name)){
            redirectAttributes.addFlashAttribute("result","Invalid name");
            return "redirect:/menu";
        }

        if(!PatternMatcher.checkNumberPattern(price)){
            redirectAttributes.addFlashAttribute("result","Invalid price");
            return "redirect:/menu";
        }

        item.setName(name);
        item.setPrice(Integer.parseInt(price));

        try{
            String imgLocation = uploadService.store(multipartFile, UUID.randomUUID().toString());
            item.setImage(imgLocation);
        }
        catch(StorageException e){
            redirectAttributes.addFlashAttribute("result",e.getMessage());
        }
        
        item.setUserId((int)session.getAttribute("userId"));
        itemService.saveItem(item);
        return "redirect:/menu";
    }

    @DeleteMapping(value = "/items/{id}")
    @ResponseBody
    public String deletePost(@PathVariable int id) {
        try{
            itemService.deleteItem(id);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }
    
}
