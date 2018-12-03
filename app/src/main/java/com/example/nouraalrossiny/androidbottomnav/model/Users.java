package com.example.nouraalrossiny.androidbottomnav.model;

public class Users {

    public  String email, name;
    long phone;

    public Users() {
    }

    public Users(String email, String name, long phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
