package com.example.imagesearchapp.repository;

import com.example.imagesearchapp.network.UnsplashAPI;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhotoRepository {
    private UnsplashAPI unsplashAPI;

    @Inject
    public PhotoRepository(UnsplashAPI unsplashAPI) {
        this.unsplashAPI = unsplashAPI;
    }
}
