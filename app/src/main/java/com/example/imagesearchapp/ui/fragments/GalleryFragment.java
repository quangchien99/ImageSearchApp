package com.example.imagesearchapp.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.imagesearchapp.R;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.network.UnsplashAPI;
import com.example.imagesearchapp.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GalleryFragment extends Fragment {
    private UnsplashAPI dataService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        getPhotos();
        return view;
    }

    private void getPhotos() {
        List<Photo> photos = new ArrayList<>();
        PhotoViewModel photoViewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        photoViewModel.fetchPhotosFromNetwork();
        photoViewModel.getmNetworkPhotos().observe(getViewLifecycleOwner(), new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> data) {
                photos.clear();
                photos.addAll(data);
                Log.d("qcpTag", "Size: " + photos.size());
            }
        });
    }
}
