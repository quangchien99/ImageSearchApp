package com.example.imagesearchapp.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.repository.PhotoRepository;

import java.util.List;

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
}
