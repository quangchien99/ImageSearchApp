package com.example.imagesearchapp.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imagesearchapp.R;
import com.example.imagesearchapp.di.NetworkModule;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.network.UnsplashAPI;
import com.example.imagesearchapp.repository.PhotoRepository;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        dataService = NetworkModule.provideAPI(NetworkModule.provideRetrofit(NetworkModule.provideGsonConverterFactory()));
        dataService.getPhotos(1, null, "lastest").enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Log.d("qcpTag", "Succeeded - code=" + response.code() + "Body size:" + response.body().size());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("qcpTag", "Failed");
            }
        });
    }
}
