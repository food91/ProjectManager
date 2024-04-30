package com.xk.porject.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.GroupInfo;

import java.util.List;

public class TwoLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GroupInfo> datas; // 假设 Category 是一个包含名称和子列表的对象

    public TwoLevelAdapter(List<GroupInfo> categories) {
        this.datas = categories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LevelOneViewHolder) {

        } else if (holder instanceof LevelTwoViewHolder) {

        }
    }

    @Override
    public int getItemViewType(int position) {
       return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class LevelOneViewHolder extends RecyclerView.ViewHolder {
        public LevelOneViewHolder(@NonNull View itemView) {
            super(itemView);
        }



    }
    static class LevelTwoViewHolder extends RecyclerView.ViewHolder {
        public LevelTwoViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
