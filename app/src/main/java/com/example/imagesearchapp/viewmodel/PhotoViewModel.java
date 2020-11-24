package com.example.imagesearchapp.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagesearchapp.constant.Const;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.repository.PhotoRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PhotoViewModel extends ViewModel {
    PhotoRepository photoRepository;
    private MutableLiveData<List<Photo>> mNetworkPhotos = new MutableLiveData<>();

    @ViewModelInject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public MutableLiveData<List<Photo>> getmNetworkPhotos() {
        return mNetworkPhotos;
    }

    public void fetchPhotosFromNetwork() {
        photoRepository.getphotos(Const.STARTING_PAGE_INDEX, 10, "latest")
                .map(searchResults -> {
                    List<Photo> photos = searchResults;
                    return photos;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> mNetworkPhotos.postValue(result), this::handleError
                );
    }

    private void handleError(Throwable error) {
        Log.d("qcpTag", "Error in fetch" + error.getMessage());
    }
}
