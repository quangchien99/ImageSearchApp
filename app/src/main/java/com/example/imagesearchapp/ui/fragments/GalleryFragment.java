package com.example.imagesearchapp.ui.fragments;

import android.app.DirectAction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.SearchManager;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagesearchapp.R;
import com.example.imagesearchapp.adapter.PhotoAdapter;
import com.example.imagesearchapp.databinding.FragmentGalleryBinding;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.models.SearchResults;
import com.example.imagesearchapp.network.UnsplashAPI;
import com.example.imagesearchapp.utils.EndlessRecyclerViewScrollListener;
import com.example.imagesearchapp.viewmodel.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GalleryFragment extends Fragment {
    private int page = 1;
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
        setHasOptionsMenu(true);
        photos = new ArrayList<>();
        photoAdapter = new PhotoAdapter(getContext());
        photoViewModel = new ViewModelProvider(requireActivity()).get(PhotoViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        fragmentGalleryBinding.rvGallery.setLayoutManager(linearLayoutManager);
        fragmentGalleryBinding.rvGallery.setAdapter(photoAdapter);
        loadPhotos();
        fragmentGalleryBinding.rvGallery.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadPhotos();
            }
        });

        return fragmentGalleryBinding.getRoot();
    }

    private void loadPhotos() {
        photoViewModel.fetchPhotosFromNetwork(page);
        photoViewModel.getmNetworkPhotos().observe(getViewLifecycleOwner(), new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> data) {
                //photos.clear();
                photos.addAll(data);
                photoAdapter.submitList(data);
                Log.d("qcpTag", "Size: " + photos.size());
            }
        });
        page++;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_gallery, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("qcpTag", "Searching for: " + query);
                photoViewModel.searchPhotoFromNetwork(query, 1);
                photoViewModel.getmSearchResults().observe(getViewLifecycleOwner(), new Observer<SearchResults>() {
                    @Override
                    public void onChanged(SearchResults data) {
                        photos.clear();
                        photos.addAll(data.getResults());
                        photoAdapter.submitList(data.getResults());
                        Log.d("qcpTag", "Size searched: " + photos.size());
                    }
                });
                search.setQuery(null, false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
