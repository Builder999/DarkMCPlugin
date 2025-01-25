package com.bubbaj2016.darkmc;

import java.util.ArrayList;
import java.util.List;

public class Job {
    int maxSlots = 1;
    String name = "";
    String category = "Civilian";
    List<String> items = new ArrayList<>();
    public Job(String name, String category, int maxSlots, List<String> items){
        this.name = name;
        this.category = category;
        this.maxSlots = maxSlots;
        this.items = items;
    }
}
