package com.ncit.emenu.feature;

import java.util.ArrayList;
import java.util.List;

public class ListMaker {
    public static List<Integer> getIntegerList(String[] str){

    List<Integer> arr = new ArrayList<>();

    for(int i=0; i<str.length; i++) {
        arr.add(Integer.parseInt(str[i]));
        }

        return arr;
    }
}
