package com.example.imagesearchapp.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgs;
import androidx.navigation.Navigation;

import com.example.imagesearchapp.databinding.FragmentDetailsBinding;
import com.example.imagesearchapp.models.Photo;

public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding fragmentDetailsBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        Photo photo = (Photo) getArguments().get("photo");
        fragmentDetailsBinding.setPhoto(photo);
        fragmentDetailsBinding.tvDescription.setText(photo.getDescription());
        Log.d("qcpTag","Descrpition:" +photo.getDescription());
        fragmentDetailsBinding.progressBarDetails.setVisibility(View.INVISIBLE);
        fragmentDetailsBinding.tvCreator.setVisibility(View.VISIBLE);
        fragmentDetailsBinding.tvDescription.setVisibility(View.VISIBLE);
        return fragmentDetailsBinding.getRoot();
    }
}
