package com.ncit.emenu.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.repository.ItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    ItemRepo itemRepo;

    @Value("${delete.absolute.path}")
    String imageParentPath;

    public void saveItem(Item item){
        itemRepo.save(item);
    }

    public List<Item> getUserItems(int userId){
        return itemRepo.findByUserId(userId);
    }

    public List<Item> getItemsByIds(List<Integer> ids){
        return itemRepo.findByItemIdIn(ids);
    }

    public Item getItemById(int id){
        return itemRepo.findById(id);
    }

    public void deleteItem(int id) throws Exception{
        String imgRelPath = itemRepo.getById(id).getImage();
        if(!imgRelPath.equals("")){
            itemRepo.deleteById(id);
            Files.deleteIfExists(Paths.get(imageParentPath+imgRelPath));
        }
    }
}
