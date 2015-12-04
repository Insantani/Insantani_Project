package com.williamhenry.insantani;

<<<<<<< HEAD
=======
import android.graphics.Bitmap;

>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
public class Cart {
    String productName;
    int qty;
    String farmer;
    float price;
<<<<<<< HEAD
    int image;


    public Cart(String productName, int qty, String farmer, float price, int image) {
=======
    Bitmap image;
    int id;
    String uom;
    int stock;


    public Cart(String productName, int qty, String farmer, float price, Bitmap image,int id, String uom, int stock) {
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
        this.productName = productName;
        this.qty = qty;
        this.farmer = farmer;
        this.price = price;
        this.image = image;
<<<<<<< HEAD
=======
        this.id=id;
        this.uom=uom;
        this.stock=stock;
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
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

<<<<<<< HEAD
    public int getImage() {

        return image;
    }
=======
    public Bitmap getImage() {

        return image;
    }
    public int getId() {

        return id;
    }
    public String getUom() {

        return uom;
    }

    public int getStock() {

        return stock;
    }
    public void setQuantity(int qty){
        this.qty=qty;
    }
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef


}
