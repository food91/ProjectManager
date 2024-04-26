package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.GroupInfo;
import com.xk.base.log.X;
import com.xk.porject.R;
import com.xk.porject.data.ExpandableListItem;

import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<ExpandableListAdapter.ViewHolder> {

    private  List<GroupInfo.Datum> dataList;
    private OnItemBindListener listener;
    public ExpandableListAdapter(List<GroupInfo.Datum> dataList,OnItemBindListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    public void setData(List<GroupInfo.Datum> dataList){
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workmanage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GroupInfo.Datum item = dataList.get(position);
        if (listener != null) {
            listener.show(holder,item, position);
        }
    }




    @Override
    public int getItemCount() {
        if(dataList==null){
            return  0;
        }

        return dataList.size();
    }

    public interface OnItemBindListener {
        void show(@NonNull ViewHolder holder,GroupInfo.Datum item, int position);
    }

    // 为列表项持有视图信息
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckBox checkBox;
        ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
