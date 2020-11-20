package com.example.imagesearchapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UnsplashPhoto implements Parcelable {
    private String id;
    private String description;
    private PhotoUrls photoUrl;
    private User user;

    public UnsplashPhoto() {
    }

    public UnsplashPhoto(String id, String description, PhotoUrls photoUrl, User user) {
        this.id = id;
        this.description = description;
        this.photoUrl = photoUrl;
        this.user = user;
    }

    protected UnsplashPhoto(Parcel in) {
        id = in.readString();
        description = in.readString();
    }

    public static final Creator<UnsplashPhoto> CREATOR = new Creator<UnsplashPhoto>() {
        @Override
        public UnsplashPhoto createFromParcel(Parcel in) {
            return new UnsplashPhoto(in);
        }

        @Override
        public UnsplashPhoto[] newArray(int size) {
            return new UnsplashPhoto[size];
        }
    };

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

    public PhotoUrls getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(PhotoUrls photoUrl) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(description);
    }
}
