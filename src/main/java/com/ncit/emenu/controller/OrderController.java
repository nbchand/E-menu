package com.ncit.emenu.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.ncit.emenu.feature.ListMaker;
import com.ncit.emenu.model.Item;
import com.ncit.emenu.model.Orders;
import com.ncit.emenu.service.ItemService;
import com.ncit.emenu.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;
    
    @GetMapping("/orders")
    public String displayOrders(HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/";
        }
        
        return "order";
    }

    @PostMapping("/create-order")
    @ResponseBody
    public String createOrder(@RequestBody String[] arr, HttpSession session){
        List<Integer> itemIds = ListMaker.getIntegerList(arr);
        if(itemIds.isEmpty()){
            return "failure";
        }
        
        Orders order = new Orders();
        order.setItemsId(itemIds);;
        order.setUserId((int)session.getAttribute("userId"));
        orderService.saveOrder(order);
        return "success";
    }
}