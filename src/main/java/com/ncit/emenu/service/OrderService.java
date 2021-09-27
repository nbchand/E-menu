package com.ncit.emenu.service;

import java.util.List;

import javax.transaction.Transactional;

import com.ncit.emenu.model.OrderItem;
import com.ncit.emenu.model.Orders;
import com.ncit.emenu.repository.ItemRepo;
import com.ncit.emenu.repository.OrderItemRepo;
import com.ncit.emenu.repository.OrderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

    public void saveOrder(Orders order){
        orderRepo.save(order);
    }

    public List<Orders> getUserOrders(int id){
        return orderRepo.findAllByUserId(id);
    }

    public void deleteOrder(int id){
        orderRepo.deleteById(id);
    }

    public int getSum(List<OrderItem> orderItems){
        int sum = 0;
        for(OrderItem orderItem: orderItems){
            sum = sum + (orderItem.getItem().getPrice()*orderItem.getQuantity());
        }
        return sum;
    }

    public List<Orders> getByItemId(int id){
        return orderRepo.findAllByOrderItemsIn(orderItemRepo.findAllByItem(itemRepo.getById(id)));
    }
    
}
