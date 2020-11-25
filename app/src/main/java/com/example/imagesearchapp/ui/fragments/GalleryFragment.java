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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.imagesearchapp.adapter.PhotoAdapter;
import com.example.imagesearchapp.databinding.FragmentGalleryBinding;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.network.UnsplashAPI;
import com.example.imagesearchapp.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GalleryFragment extends Fragment {
    private UnsplashAPI dataService;
    private List<Photo> photos;
    private PhotoAdapter photoAdapter;
    private PhotoViewModel photoViewModel;
    private FragmentGalleryBinding fragmentGalleryBinding;

    public GalleryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentGalleryBinding = FragmentGalleryBinding.inflate(inflater, container, false);
        photos = new ArrayList<>();
        photoAdapter = new PhotoAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragmentGalleryBinding.rvGallery.setLayoutManager(linearLayoutManager);
        fragmentGalleryBinding.rvGallery.setAdapter(photoAdapter);

        photoViewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        photoViewModel.fetchPhotosFromNetwork();
        photoViewModel.getmNetworkPhotos().observe(getViewLifecycleOwner(), new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> data) {
                photos.clear();
                photos.addAll(data);
                photoAdapter.submitList(data);
                Log.d("qcpTag", "Size: " + photos.size());
            }
        });
        return fragmentGalleryBinding.getRoot();
    }
}
