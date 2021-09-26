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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String displayOrders(Model model, HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/";
        }

        List<Orders> orders = orderService.getUserOrders((int)session.getAttribute("userId"));

        model.addAttribute("orders", orders);
        
        return "order";
    }

    @PostMapping("/create-order")
    @ResponseBody
    public String createOrder(@RequestBody String[] arr, HttpSession session){
        List<Integer> itemIds = ListMaker.getIntegerList(arr);
        List<Item> items = itemService.getItemsByIds(itemIds);
        if(itemIds.isEmpty()){
            return "failure";
        }

        Orders order = new Orders();
        order.setItems(items);
        order.setUserId((int)session.getAttribute("userId"));
        orderService.saveOrder(order);
        return "success";
    }


    @DeleteMapping(value = "/orders/{id}")
    @ResponseBody
    public String deletePost(@PathVariable int id) {

        orderService.deleteOrder(id);
        return "success";
    }
}