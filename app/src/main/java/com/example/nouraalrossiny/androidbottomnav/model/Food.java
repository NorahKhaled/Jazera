package com.example.nouraalrossiny.androidbottomnav.model;

public class Food {
    private String Fcode, Name, Unit, Price, Code, MenuId, Barcode, Image , Discount;

    public Food() {
    }

    public Food(String fcode, String name, String unit, String price, String code, String menuId, String barcode, String image ,String discount) {
        Fcode = fcode;
        Name = name;
        Unit = unit;
        Price = price;
        Code = code;
        MenuId = menuId;
        Barcode = barcode;
        Image = image;
        Discount = discount;
    }

    public String getFcode() { return Fcode; }

    public void setFcode(String fcode) { Fcode = fcode; }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public String getUnit() { return Unit; }

    public void setUnit(String unit) { Unit = unit; }

    public String getPrice() { return Price; }

    public void setPrice(String price) { Price = price; }

    public String getCode() { return Code; }

    public void setCode(String code) { Code = code; }

    public String getMenuId() { return MenuId; }

    public void setMenuId(String menuId) { MenuId = menuId; }

    public String getBarcode() { return Barcode; }

    public void setBarcode(String barcode) { Barcode = barcode; }

    public String getImage() { return Image; }

    public void setImage(String image) { Image = image; }

    public String getDiscount() {
        return Discount;
    }
    public void setDiscount(String discount){
        Discount = discount;
    }
}
