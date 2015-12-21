package com.williamhenry.insantani;

import android.graphics.Bitmap;

public class SearchItem {

    private String title;
    private String content;
    private Bitmap picture;
    private int distance;

    public SearchItem() {
    }

    public SearchItem(String title, String content, Bitmap picture, int distance) {
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.distance=distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Bitmap getPicture(){
        return picture;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance(){
        return distance;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

}
