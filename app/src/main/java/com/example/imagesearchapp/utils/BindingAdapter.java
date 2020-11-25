package com.example.imagesearchapp.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.imagesearchapp.R;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("set_src")
    public static void setImage(ImageView imgView, String link) {
        Glide.with(imgView).load(link)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.mipmap.ic_launcher)
                .into(imgView);
    }
}
