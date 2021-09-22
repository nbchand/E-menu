package com.ncit.emenu.service;

import java.util.List;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.repository.ItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    ItemRepo itemRepo;

    public void saveItem(Item item){
        itemRepo.save(item);
    }

    public List<Item> getUserItems(int userId){
        return itemRepo.findByUserId(userId);
    }
}
