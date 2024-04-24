package com.xk.porject.home.adaoter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xk.porject.R;
import com.xk.porject.data.DataItem;

import java.util.List;


public class SlideshowAdapter extends RecyclerView.Adapter<SlideshowAdapter.SlideshowViewHolder> {
    private List<DataItem> data;
    private Context context;

    public SlideshowAdapter(Context context, List<DataItem> data) {
        this.data = data;
        this.context =context;
    }

    public void setData( List<DataItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SlideshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideshowViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slideshow_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SlideshowViewHolder holder, int position) {
        Glide.with(context).load(R.drawable.normal_u2).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SlideshowViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public SlideshowViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}