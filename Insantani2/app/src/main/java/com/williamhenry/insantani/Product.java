package com.williamhenry.insantani;

/**
 * Created by william on 10/17/2015.
 */


/**
 * Created by william on 10/17/2015.
 */
public class Product {
    private String mName;
    private int mThumbnail;
    private String desc;
    private String fname;
    private int price;
    private int stock;
    private int id;
    private String url;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }
}

