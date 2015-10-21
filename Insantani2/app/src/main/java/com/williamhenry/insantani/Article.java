package com.williamhenry.insantani;

/**
 * Created by agungwy on 10/20/2015.
 */
public class Article {

    String author;
    String title;
    String description;
    int image;

    public Article(String author, String title, String description, int image) {
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

    public int getImage() {
        return image;
    }
}
