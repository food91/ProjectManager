package com.xk.porject.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xk.base.data.ResponseAllWorkData;
import com.xk.porject.data.ProjectPersonnel;
import com.xk.porject.databinding.ProjectpersonnerlItemBinding;


import java.util.List;
public class ProjectPersonnelAdapter extends RecyclerView.Adapter<ProjectPersonnelAdapter.ViewHolder> {

    private List<ResponseAllWorkData.Data.Project> personnelList;
    private OnBindDataListener onBindDataListener;

    // 构造函数
    public ProjectPersonnelAdapter(List<ResponseAllWorkData.Data.Project> personnelList,
                                   OnBindDataListener onBindDataListener) {
        this.personnelList = personnelList;
        this.onBindDataListener=onBindDataListener;
    }

    public void setData(List<ResponseAllWorkData.Data.Project> data){
        this.personnelList = data;
        notifyDataSetChanged();
    }

    // 创建新视图（由布局管理器调用）
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 使用View Binding加载布局
        ProjectpersonnerlItemBinding binding = ProjectpersonnerlItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    // 将数据绑定到视图上（由布局管理器调用）
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindDataListener.onBindData(holder,position,personnelList.get(position));

    }

    // 返回数据集的大小（由布局管理器调用）
    @Override
    public int getItemCount() {
        return personnelList.size();
    }

    // 提供对视图的引用
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 使用View Binding
        public final ProjectpersonnerlItemBinding binding;

        public ViewHolder(ProjectpersonnerlItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
