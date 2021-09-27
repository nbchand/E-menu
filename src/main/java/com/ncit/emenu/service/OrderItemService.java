package com.ncit.emenu.service;

import java.util.List;

import com.ncit.emenu.model.OrderItem;
import com.ncit.emenu.repository.OrderItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepo orderItemRepo;

    public void saveAllOrderItem(List<OrderItem> orderItems){
        orderItemRepo.saveAll(orderItems);
    }
}
