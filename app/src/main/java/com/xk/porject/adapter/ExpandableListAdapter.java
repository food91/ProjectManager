package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.GroupInfo;
import com.xk.porject.R;

public class ExpandableListAdapter extends RecyclerView.Adapter<ExpandableListAdapter.ViewHolder> {

    private  GroupInfo.Data data;
    private OnItemBindListener listener;
    private int TYPE_GROUP = 0;
    private int TYPE_WORKER =1;
    private int groupCount = 0;
    private int workerCount = 0;
    public ExpandableListAdapter(GroupInfo.Data data,OnItemBindListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public void setData(GroupInfo.Data dataList){
        this.data = dataList;
        if (data != null) {
            groupCount = data.getGroup() != null ? data.getGroup().size() : 0;
            workerCount = data.getWorker() != null ? data.getWorker().size() : 0;
        } else {
            groupCount = 0;
            workerCount = 0;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position < groupCount ? TYPE_GROUP : TYPE_WORKER;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==TYPE_GROUP){
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workmanage, parent, false);
        }else{
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_worker, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (listener == null) {
            return; // 提前退出，避免无效操作
        }
        // 假设已经在构造函数或某个初始化方法中计算了groupSize和workerSize
        if (position < groupCount) {
            GroupInfo.Group item = data.getGroup().get(position);
            listener.showGroup(holder, item, position);
        } else if (position < groupCount + workerCount) { // 更明确的条件判断
            GroupInfo.Worker itemw = data.getWorker().get(position - groupCount);
            listener.showWork(holder, itemw, position);
        }
    }




    @Override
    public int getItemCount() {
        if(data==null){
            return  0;
        }
        return groupCount + workerCount;
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

    public static class WorkViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckBox checkBox;
        private ImageView iv;
        WorkViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);
            checkBox = itemView.findViewById(R.id.checkbox);
            iv = itemView.findViewById(R.id.iv_rank);
        }
    }
}
