package com.williamhenry.insantani;

import java.io.Serializable;

/**
 * Created by william on 12/12/2015.
 */
public class ProductFarmer implements Serializable {
    public String farmer_username;
    public double distance;
    public double lat;
    public double lng;

    public ProductFarmer(String farmer_username, double distance,double lat,double lng){
        this.farmer_username=farmer_username;
        this.distance=distance;
        this.lat=lat;
        this.lng=lng;
    }
    public String getFarmerUsername(){
        return farmer_username;
    }
    public double getLat(){
        return lat;
    }
    public double getLng(){
        return lng;
    }
    public  double getDistance(){
        return distance;
    }
}
