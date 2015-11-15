package com.williamhenry.insantani;

import android.graphics.Bitmap;

/**
 * Created by agungwy on 10/20/2015.
 */
public class Article {

    String author;
    String title;
    String description;
    Bitmap image;

    public Article(String author, String title, String description, Bitmap image) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public Bitmap getImage() {
        return image;
    }
}
