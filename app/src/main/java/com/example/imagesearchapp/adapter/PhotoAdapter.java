package com.example.imagesearchapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagesearchapp.R;
import com.example.imagesearchapp.databinding.ItemPhotoBinding;
import com.example.imagesearchapp.models.Photo;
import com.example.imagesearchapp.utils.ItemClickListener;

public class PhotoAdapter extends ListAdapter<Photo, PhotoAdapter.ViewHolder> {
    private Context context;
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

    public PhotoAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
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
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, "Long Click in position: " + position, Toast.LENGTH_LONG).show();
                    
                } else {
                    Log.d("qcpTag", "Short click in item " + position);
                    Photo photo = getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("photo", photo);
                    Navigation.findNavController(view).navigate(R.id.action_galleryFragment_to_detailsFragment, bundle);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ItemPhotoBinding itemPhotoBinding;
        private ItemClickListener itemClickListener;

        public ViewHolder(ItemPhotoBinding itemPhotoBinding) {
            super(itemPhotoBinding.getRoot());
            this.itemPhotoBinding = itemPhotoBinding;
            itemPhotoBinding.imgPhoto.setOnClickListener(this);
            itemPhotoBinding.imgPhoto.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getBindingAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onItemClick(v, getBindingAdapterPosition(), true);
            return true;
        }

        public void bind(Photo photo) {
            itemPhotoBinding.setPhoto(photo);
            itemPhotoBinding.executePendingBindings();
        }
    }
}
