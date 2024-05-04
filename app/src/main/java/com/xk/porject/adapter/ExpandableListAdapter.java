package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.GroupInfo;
import com.xk.porject.R;

import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<ExpandableListAdapter.ViewHolder> {

    private  GroupInfo.Data data;
    private OnItemBindListener listener;
    public ExpandableListAdapter(GroupInfo.Data data,OnItemBindListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public void setData(GroupInfo.Data dataList){
        this.data = dataList;
        notifyDataSetChanged();
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
        if (listener != null) {
            if(position<data.getGroup().size()){
                GroupInfo.Group item = data.getGroup().get(position);
                listener.showGroup(holder,item, position);
            }else{
                GroupInfo.Worker itemw = data.getWorker().get(position);
                listener.showWork(holder,itemw, position);
            }
        }
    }




    @Override
    public int getItemCount() {
        if(data==null){
            return  0;
        }

        int worksize=0;
        int sum=0;
        if(data.getWorker()!=null){
            sum +=data.getWorker().size();
        }else{

        }
        if(data.getGroup()!=null){
            sum +=data.getGroup().size();
        }
        return sum;
    }

    public interface OnItemBindListener {
        void showGroup(@NonNull ViewHolder holder, GroupInfo.Group item, int position);
        void showWork(@NonNull ViewHolder holder, GroupInfo.Worker item, int position);
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
