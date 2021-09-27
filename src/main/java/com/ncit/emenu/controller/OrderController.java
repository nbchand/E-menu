package com.ncit.emenu.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ncit.emenu.model.OrderItem;
import com.ncit.emenu.model.Orders;
import com.ncit.emenu.pojo.ItemQuantity;
import com.ncit.emenu.service.ItemService;
import com.ncit.emenu.service.OrderItemService;
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

    @Autowired
    OrderItemService orderItemService;
    
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
    public String createOrder(@RequestBody ItemQuantity json, HttpSession session){

        if(json.getItems().isEmpty()){
            return "failure";
        }

        List<OrderItem> orderItems = new ArrayList<>();
        System.out.println("Outside");
        for(int i=0; i<json.getItems().size(); i++){

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(itemService.getItemById(json.getItems().get(i)));
            orderItem.setQuantity(json.getOccurance().get(i));

            orderItems.add(orderItem);
        }

        Orders order = new Orders();
        order.setUserId((int)session.getAttribute("userId"));
        orderItemService.saveAllOrderItem(orderItems);
        order.setTotal(orderService.getSum(orderItems));
        order.setOrderItems(orderItems);
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