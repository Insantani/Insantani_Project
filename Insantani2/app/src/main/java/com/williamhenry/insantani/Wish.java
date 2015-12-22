package com.williamhenry.insantani;

import android.graphics.Bitmap;

public class Wish {
    String productName;
    String farmer;
    int price;
    Bitmap image;
    int id;
    String uom;


    public Wish(String productName, String farmer, int price, Bitmap image, int id, String uom) {
        this.productName = productName;
        this.farmer = farmer;
        this.price = price;
        this.image = image;
        this.id=id;
        this.uom=uom;
    }

    public String getProductName() {

        return productName;
    }

    public String getFarmer() {

        return farmer;
    }

    public int getPrice() {

        return price;
    }

    public Bitmap getImage() {

        return image;
    }
    public int getId(){
        return id;
    }

    public String getUom(){
        return "/ "+uom;
    }


}