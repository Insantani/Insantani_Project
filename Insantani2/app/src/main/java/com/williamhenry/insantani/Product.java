package com.williamhenry.insantani;

/**
 * Created by william on 10/17/2015.
 */


<<<<<<< HEAD
import android.graphics.Bitmap;

=======
>>>>>>> 84873fb19a053bb263e248294c0f1b23b4be06a3
/**
 * Created by william on 10/17/2015.
 */
public class Product {
    private String mName;
<<<<<<< HEAD
    private Bitmap mThumbnail;
=======
    private int mThumbnail;
>>>>>>> 84873fb19a053bb263e248294c0f1b23b4be06a3
    private String desc;
    private String fname;
    private int price;
    private int stock;
    private int id;
    private String url;
<<<<<<< HEAD
    private String uom;
=======
>>>>>>> 84873fb19a053bb263e248294c0f1b23b4be06a3

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

<<<<<<< HEAD
    public Bitmap getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
=======
    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
>>>>>>> 84873fb19a053bb263e248294c0f1b23b4be06a3
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
<<<<<<< HEAD

    public void setUom(String uom){
        this.uom = uom;
    }

    public String getUom(){
        return uom;
    }
=======
>>>>>>> 84873fb19a053bb263e248294c0f1b23b4be06a3
}

