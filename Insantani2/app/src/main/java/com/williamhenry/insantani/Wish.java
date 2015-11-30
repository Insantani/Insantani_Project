package com.williamhenry.insantani;

public class Wish {
    String productName;
    String farmer;
    float price;
    int image;


    public Wish(String productName, String farmer, float price, int image) {
        this.productName = productName;
        this.farmer = farmer;
        this.price = price;
        this.image = image;
    }

    public String getProductName() {

        return productName;
    }

    public String getFarmer() {

        return farmer;
    }

    public float getPrice() {

        return price;
    }

    public int getImage() {

        return image;
    }


}
