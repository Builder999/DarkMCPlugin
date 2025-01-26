package com.bubbaj2016.darkmc;

import java.util.ArrayList;
import java.util.List;

public class Job {
    int maxSlots = 1;
    String name = "";
    String category = "Civilian";
    ArrayList<String> items = new ArrayList<>();
    public Job(String name, String category, int maxSlots, List<String> items){
        this.name = name;
        this.category = category;
        this.maxSlots = maxSlots;
        this.items = new ArrayList<>(items);
    }

    public String getName(){
        return name;
    }
    public ArrayList<String> getitems(){
        return items;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Job){
            Job job = (Job) obj;
            if (this.name.equals(job.name) && this.category.equals(job.category) && this.maxSlots == job.maxSlots && this.items.equals(job.items)){
                return true;
            }
        }
        return false;
    }
}
