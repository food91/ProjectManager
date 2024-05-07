package com.xk.porject.adapter;

import com.xk.base.data.ResponseAllWorkData;
import com.xk.porject.data.ProjectPersonnel;

public interface OnBindDataListener {
    void onBindData(ProjectPersonnelAdapter.ViewHolder holder, int position, ResponseAllWorkData.Data.Project personnel);
}
