package com.williamhenry.insantani;

import android.graphics.Bitmap;

public class Cart {
    String productName;
    int qty;
    String farmer;
    float price;
    Bitmap image;
    int id;


    public Cart(String productName, int qty, String farmer, float price, Bitmap image,int id) {
        this.productName = productName;
        this.qty = qty;
        this.farmer = farmer;
        this.price = price;
        this.image = image;
        this.id=id;
    }

    public String getProductName() {

        return productName;
    }

    public int getQty() {
        return qty;
    }

    public String getFarmer() {

        return farmer;
    }

    public float getPrice() {

        return price;
    }

    public Bitmap getImage() {

        return image;
    }
    public int getId() {

        return id;
    }
    public void setQuantity(int qty){
        this.qty=qty;
    }


}
