package com.ncit.emenu.pojo;

import java.util.List;

public class ItemQuantity {
    private List<Integer> items;
    private List<Integer> occurance;


    public List<Integer> getItems() {
        return this.items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public List<Integer> getOccurance() {
        return this.occurance;
    }

    public void setOccurance(List<Integer> occurance) {
        this.occurance = occurance;
    }

}
