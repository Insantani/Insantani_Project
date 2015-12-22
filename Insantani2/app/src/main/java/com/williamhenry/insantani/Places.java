package com.williamhenry.insantani;

/**
 * Created by william on 12/12/2015.
 */
public class Places {
    private String name;
    private double lat;
    private double lng;
    private String place_id;
    private String address;
    private String phone_number;
    private String vicinity;

    public Places(String name, double lat, double lng, String place_id, String vicinity) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.place_id = place_id;
        this.vicinity = vicinity;
        this.address = "";
        this.phone_number = "";

    }

    public Places(String name, double lat, double lng, String place_id, String address, String phone_number) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.place_id = place_id;
        this.address = address;
        this.phone_number = phone_number;
        this.vicinity = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
