package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.porject.R;
import com.xk.porject.data.ExpandableListItem;

import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<ExpandableListAdapter.ViewHolder> {

    private final List<ExpandableListItem> dataList;

    public ExpandableListAdapter(List<ExpandableListItem> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workmanage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpandableListItem item = dataList.get(position);
        holder.title.setText(item.getTitle());

        // 点击监听器，用来展开或折叠列表项
        holder.itemView.setOnClickListener(v -> {
            item.toggleExpanded();
            if (item.isExpanded()) {
                expandItem(position);
            } else {
           //     collapseItem(position);
            }
        });
    }

    private void expandItem(int position) {
        ExpandableListItem parentItem = dataList.get(position);
        int count = parentItem.getChildren().size();
        if (count == 0) {
            // 如果没有子项，则不进行任何操作
            return;
        }
        // 移除父项并通知适配器
        dataList.clear();
        notifyDataSetChanged();
        // 添加子项到列表中并通知适配器
        for (int i = 0; i < count; i++) {
            dataList.add( i, parentItem.getChildren().get(i));
            notifyItemInserted(  i);
        }
    }

    // 折叠列表项，移除其所有子项
    private void collapseItem(int position) {
        ExpandableListItem item = dataList.get(position);
        int count = item.getChildren().size();
        for (int i = 0; i < count; i++) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
        // 重新添加父项到原来的位置
        dataList.add(position, item);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 为列表项持有视图信息
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
