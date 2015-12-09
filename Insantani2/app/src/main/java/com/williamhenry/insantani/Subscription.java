package com.williamhenry.insantani;

public class Subscription {
    String farmername;
    int image;
    boolean following;


    public Subscription(String farmername, int image, boolean following) {
        this.farmername = farmername;
        this.image = image;
        this.following = following;
    }

    public String getFarmerName() {

        return farmername;
    }

    public int getImage() {

        return image;
    }

    public boolean getFollowing(){
        return following;
    }

}
