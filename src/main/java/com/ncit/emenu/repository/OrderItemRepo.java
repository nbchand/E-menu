package com.ncit.emenu.repository;

import java.util.List;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.model.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByItem(Item item);
    void deleteAllByItem(Item item);
}
