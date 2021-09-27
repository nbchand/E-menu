package com.ncit.emenu.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import com.ncit.emenu.model.Item;
import com.ncit.emenu.repository.ItemRepo;
import com.ncit.emenu.repository.OrderItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ItemService {
    
    @Autowired
    ItemRepo itemRepo;

    @Autowired
    OrderItemRepo orderItemRepo;

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

        System.out.println("Outside");

        if(!orderItemRepo.findAllByItem(this.getItemById(id)).isEmpty()){
            System.out.println("Inside");
            orderItemRepo.deleteAllByItem(this.getItemById(id));
        }
        System.out.println("outside again");
        itemRepo.deleteById(id);

        if(!(imgRelPath==null)){
            Files.deleteIfExists(Paths.get(imageParentPath+imgRelPath));
        }
    }
}
