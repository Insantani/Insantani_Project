package com.williamhenry.insantani;

/**
 * Created by dioviazalia on 11/3/15.
 */
public class Cart {
    String productName;
    int qty;
    String farmer;
    float price;
    int image;

    public Cart(String productName, int qty, String farmer, float price, int image) {
        this.productName = productName;
        this.qty = qty;
        this.farmer = farmer;
        this.price = price;
        this.image = image;
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

    public int getImage() {

        return image;
    }


}
