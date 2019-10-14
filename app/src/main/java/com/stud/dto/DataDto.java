package com.stud.dto;

import java.util.Date;

public class DataDto implements Comparable<DataDto>{

    public String key;
    public String data;
    public Date date;


    @Override
    public int compareTo(DataDto o) {
        return this.date.compareTo(o.date);
    }
}
