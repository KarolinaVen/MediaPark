package com.example.rozhe.mediapark;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("photoUrl")
    private String photoUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
