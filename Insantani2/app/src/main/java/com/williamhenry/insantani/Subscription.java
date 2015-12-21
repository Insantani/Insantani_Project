package com.williamhenry.insantani;

import android.graphics.Bitmap;

public class Subscription {
    String farmername;
    Bitmap image;
    boolean following;


    public Subscription(String farmername, Bitmap image, boolean following) {
        this.farmername = farmername;
        this.image = image;
        this.following = following;
    }

    public String getFarmerName() {

        return farmername;
    }

    public Bitmap getImage() {

        return image;
    }

    public boolean getFollowing(){
        return following;
    }
    public void setFollowing(boolean following){
        this.following=following;
    }

}