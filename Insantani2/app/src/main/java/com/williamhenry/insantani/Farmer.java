package com.williamhenry.insantani;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by agungwy on 10/20/2015.
 */
public class Farmer {

    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private ArrayList<Integer> photo;
    private Bitmap photoProfile;
    private int photoBackground;
    private ArrayList<Product> products;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<Integer> photo) {
        this.photo = photo;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


    public Bitmap getPhotoProfile() {
        return photoProfile;
    }


    public int getPhotoBackground() {
        return photoBackground;
    }

    public void setPhotoBackground(int photoBackground) {
        this.photoBackground = photoBackground;
    }

    public void setPhotoProfile(Bitmap photoProfile) {
        this.photoProfile = photoProfile;
    }
}
