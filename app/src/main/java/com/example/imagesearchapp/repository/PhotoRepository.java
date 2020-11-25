package com.example.imagesearchapp.repository;

import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.models.SearchResults;
import com.example.imagesearchapp.network.UnsplashAPI;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class PhotoRepository {
    private UnsplashAPI unsplashAPI;

    @Inject
    public PhotoRepository(UnsplashAPI unsplashAPI) {
        this.unsplashAPI = unsplashAPI;
    }

    public Observable<List<Photo>> getphotos(int page, int perPage, String order) {
        return unsplashAPI.getPhotos(page, perPage, order);
    }

    public Observable<SearchResults> searchPhotos(String query, int page, int perPage, String orientation) {
        return unsplashAPI.searchPhotos(query, page, perPage, orientation);
    }
}
