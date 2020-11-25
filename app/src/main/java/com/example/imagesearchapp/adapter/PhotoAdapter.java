package com.example.imagesearchapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagesearchapp.databinding.ItemPhotoBinding;
import com.example.imagesearchapp.models.Photo;

public class PhotoAdapter extends ListAdapter<Photo, PhotoAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);
        }
    };

    public PhotoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding itemPhotoBinding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemPhotoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPhotoBinding itemPhotoBinding;

        public ViewHolder(ItemPhotoBinding itemPhotoBinding) {
            super(itemPhotoBinding.getRoot());
            this.itemPhotoBinding = itemPhotoBinding;
        }

        public void bind(Photo photo) {
            itemPhotoBinding.setPhoto(photo);
            itemPhotoBinding.executePendingBindings();
        }
    }
}
