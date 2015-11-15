package com.williamhenry.insantani;

/**
 * Created by william on 10/17/2015.
 */


import android.graphics.Bitmap;

/**
 * Created by william on 10/17/2015.
 */
public class Product {
    private String mName;
    private Bitmap mThumbnail;
    private String desc;
    private String fname;
    private int price;
    private int stock;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public void setDescription(String desc){
        this.desc = desc;
    }

    public String getDescription(){
        return desc;
    }

    public void setFarmerName(String fname){
        this.fname = fname;
    }

    public String getFarmerName(){
        return fname;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStock(){
        return stock;
    }
}

