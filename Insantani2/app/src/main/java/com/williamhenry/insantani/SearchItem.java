package com.williamhenry.insantani;

<<<<<<< HEAD
=======
import android.graphics.Bitmap;

>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
public class SearchItem {

    private String title;
    private String content;
<<<<<<< HEAD
    private int picture;
=======
    private Bitmap picture;
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef

    public SearchItem() {
    }

<<<<<<< HEAD
    public SearchItem(String title, String content, int picture) {
=======
    public SearchItem(String title, String content, Bitmap picture) {
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
        this.title = title;
        this.content = content;
        this.picture = picture;
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

<<<<<<< HEAD
    public int getPicture(){
        return picture;
    }

    public void setPicture(int picture) {
=======
    public Bitmap getPicture(){
        return picture;
    }

    public void setPicture(Bitmap picture) {
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
        this.picture = picture;
    }

}
