package com.ncit.emenu.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Orders implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int orderId;
    
    @Column(name = "order_user_id")
    private int userId;

    // @Column
    // @ElementCollection
    // @CollectionTable(name = "order_item_id",
    //                 joinColumns = @JoinColumn(name = "order_id"))
    // private List<Integer> itemsId;

    @ManyToMany
    private List<Item> items;


    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "{" +
            " orderId='" + getOrderId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", items='" + getItems() + "'" +
            "}";
    }



}
