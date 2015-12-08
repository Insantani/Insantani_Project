package com.williamhenry.insantani;

import android.graphics.Bitmap;

import java.security.Timestamp;

/**
 * Created by agungwy on 30-Nov-15.
 */
public class FarmerActivity {

    private String name;
    private String detail;
    private Bitmap photo;
    private Timestamp time;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
