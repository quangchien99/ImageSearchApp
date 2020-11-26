package com.example.imagesearchapp.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagesearchapp.constant.Const;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.models.SearchResults;
import com.example.imagesearchapp.repository.PhotoRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PhotoViewModel extends ViewModel {
    PhotoRepository photoRepository;
    private MutableLiveData<List<Photo>> mNetworkPhotos = new MutableLiveData<>();
    private MutableLiveData<SearchResults> mSearchResults = new MutableLiveData<>();

    @ViewModelInject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public MutableLiveData<List<Photo>> getmNetworkPhotos() {
        return mNetworkPhotos;
    }

    public MutableLiveData<SearchResults> getmSearchResults() {
        return mSearchResults;
    }

    public void fetchPhotosFromNetwork(int page) {
        photoRepository.getphotos(page, 10, "latest")
                .map(searchResults -> {
                    List<Photo> photos = searchResults;
                    return photos;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> mNetworkPhotos.postValue(result), this::handleError
                );
    }

    public void searchPhotoFromNetwork(String query, int page) {
        photoRepository.searchPhotos(query, page, 10, null)
                .map(searchResults -> {
                    SearchResults photos = searchResults;
                    return photos;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> mSearchResults.postValue(result), this::handleError);
    }

    private void handleError(Throwable error) {
        Log.d("qcpTag", "Error in Viewmodel:" + error.getMessage());
    }
}
