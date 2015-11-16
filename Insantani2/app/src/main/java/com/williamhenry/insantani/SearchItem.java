package com.williamhenry.insantani;

public class SearchItem {

    private String title;
    private String content;
    private int picture;

    public SearchItem() {
    }

    public SearchItem(String title, String content, int picture) {
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

    public int getPicture(){
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
