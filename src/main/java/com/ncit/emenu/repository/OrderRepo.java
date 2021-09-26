package com.ncit.emenu.repository;

import java.util.List;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {
    
    List<Orders> findAllByUserId(int id);
    List<Orders> findAllByItemsIn(List<Item> items);
}
