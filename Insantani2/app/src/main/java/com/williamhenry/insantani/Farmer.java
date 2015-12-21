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
    private Bitmap photoBackground;
    private float rating;
    private String profilePictureUrl;
    private double distance;
    private String fullName;
//    private String backgroundPictureUrl;
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


    public Bitmap getPhotoBackground() {
        return photoBackground;
    }

    public void setPhotoBackground(Bitmap photoBackground) {
        this.photoBackground = photoBackground;
    }

    public void setPhotoProfile(Bitmap photoProfile) {
        this.photoProfile = photoProfile;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
