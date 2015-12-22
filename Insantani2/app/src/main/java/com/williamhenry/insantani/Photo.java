package com.williamhenry.insantani;

import android.graphics.Bitmap;

/**
 * Created by william on 12/5/2015.
 */
public class Photo {
    private String url;
    private Bitmap image;

    public Bitmap getImage(){
        return image;
    }
    public void setImage(Bitmap image){
        this.image=image;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }
}
