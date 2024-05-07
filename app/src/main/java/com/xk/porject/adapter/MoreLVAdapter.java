package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.ResponseFindProjectList;
import com.xk.porject.R;
import com.xk.porject.databinding.ItemMoreadapterTitleBinding;
import com.xk.porject.databinding.ItemProjectlistBinding;
import com.xk.porject.databinding.ItemRpojectWorkerBinding;

import java.util.List;

public class MoreLVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ResponseFindProjectList.Data data;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_moreadapter_title, parent, false);
                return new HeaderViewHolder(view);
            case TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rpoject_worker, parent, false);
                return new NormalViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int sum=1;
        if(data.getContract()==null){

        }else{
            sum+=data.getContract().size();
        }
        sum+=data.getProject().size();
        return 0;
    }
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final ItemMoreadapterTitleBinding binding;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMoreadapterTitleBinding.bind(itemView);
        }

        public ItemMoreadapterTitleBinding getBinding() {
            return binding;
        }
    }

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        private final ItemRpojectWorkerBinding binding;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemRpojectWorkerBinding.bind(itemView);
        }

        public ItemRpojectWorkerBinding getBinding() {
            return binding;
        }
    }
}
