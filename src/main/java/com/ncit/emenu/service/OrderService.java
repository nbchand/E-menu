package com.ncit.emenu.service;

import java.util.List;

import javax.transaction.Transactional;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.model.Orders;
import com.ncit.emenu.repository.ItemRepo;
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

    public void saveOrder(Orders order){
        orderRepo.save(order);
    }

    public List<Item> getItemsByIds(List<Integer> ids){
        return itemRepo.findByItemIdIn(ids);
    }

    public List<Orders> getUserOrders(int id){
        return orderRepo.findAllByUserId(id);
    }

    public void deleteOrder(int id){
        orderRepo.deleteById(id);
    }
    
}
