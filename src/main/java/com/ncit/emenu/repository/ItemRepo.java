package com.ncit.emenu.repository;

import java.util.List;

import com.ncit.emenu.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findByUserId(int id);

    //send lits of Item when list of itemId are provided
    List<Item> findByItemIdIn(List<Integer> ids);
}
