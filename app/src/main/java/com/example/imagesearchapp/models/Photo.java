package com.example.imagesearchapp.models;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Photo implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("urls")
    @Expose
    private Urls photoUrl;
    @SerializedName("user")
    @Expose
    private User user;

    public Photo() {
    }

    public Photo(String id, String description, Urls photoUrl, User user) {
        this.id = id;
        this.description = description;
        this.photoUrl = photoUrl;
        this.user = user;
    }

    protected Photo(Parcel in) {
        id = in.readString();
        description = in.readString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Urls getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Urls photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UnsplashPhoto{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", photoUrl=" + photoUrl +
                ", user=" + user +
                '}';
    }

}
