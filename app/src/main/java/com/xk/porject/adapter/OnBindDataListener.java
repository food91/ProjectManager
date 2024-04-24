package com.xk.porject.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.xk.porject.data.ProjectPersonnel;
import com.xk.porject.home.adaoter.ViewHolder;

public interface OnBindDataListener {
    void onBindData(ProjectPersonnelAdapter.ViewHolder holder, int position, ProjectPersonnel personnel);
}
