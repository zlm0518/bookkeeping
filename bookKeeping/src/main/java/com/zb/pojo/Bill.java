package com.zb.pojo;

import java.util.Date;

public class Bill {
    private Integer id;
    private String title;
    private Date bill_time;
    private Integer type_id;
    private Integer price;
    private String explain;
    private String type_price;
    private String type_name;


    public Bill(){}

    public Bill ( Integer id, String title, Date bill_time, Integer type_id, Integer price, String explain, String type_price, String type_name ) {
        this.id = id;
        this.title = title;
        this.bill_time = bill_time;
        this.type_id = type_id;
        this.price = price;
        this.explain = explain;
        this.type_price = type_price;
        this.type_name = type_name;
    }

    public Integer getId () {
        return id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public Date getBill_time () {
        return bill_time;
    }

    public void setBill_time ( Date bill_time ) {
        this.bill_time = bill_time;
    }

    public Integer getType_id () {
        return type_id;
    }

    public void setType_id ( Integer type_id ) {
        this.type_id = type_id;
    }

    public Integer getPrice () {
        return price;
    }

    public void setPrice ( Integer price ) {
        this.price = price;
    }

    public String getExplain () {
        return explain;
    }

    public void setExplain ( String explain ) {
        this.explain = explain;
    }

    public String getType_price () {
        String name = null;
        switch (type_id){
            case 1:
                name="-";
                break;
            case 2:
                name="+";
                break;
            case 3:
                name="+";
                break;
            case 4:
                name="-";
                break;
            case 5:
                name="+";
                break;
            case 6:
                name="+";
                break;
            case 7:
                name="-";
                break;
        }
        return name+price+"元";
    }


    public void setType_price ( String type_price ) {
        this.type_price = type_price;
    }

    public String getType_name () {
        return type_name;
    }

    public void setType_name ( String type_name ) {
        this.type_name = type_name;
    }
}
