package com.example.nouraalrossiny.androidbottomnav;

public class Users {

    public  String email, name;
    long phone;

    public Users(String email, String name, String phone){ //reading values back

    }

    public String getEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
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

    public Users(String name, String email, long phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
