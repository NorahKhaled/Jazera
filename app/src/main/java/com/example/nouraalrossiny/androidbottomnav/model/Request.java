package com.example.nouraalrossiny.androidbottomnav.model;

import java.util.List;

public class Request {
    private String phone ;
    private String name ;
    private String address ;
    private String total ;
    private List<Order> foods ; // list of food order


    public Request(){

    }

    public Request(String phone , String name , String address , String total , List<Order> foods){
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.foods = foods;

    }


    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getTotal() {
        return total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public String getAddress() {
        return address;
    }

}
