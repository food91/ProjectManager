package com.xk.porject.home.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xk.porject.R;
import com.xk.porject.adapter.OnBindDataListener;
import com.xk.porject.adapter.ProjectPersonnelAdapter;
import com.xk.porject.data.ProjectPersonnel;
import com.xk.porject.databinding.FragmentProjectPersonnelBinding;
import com.xk.porject.databinding.ProjectpersonnerlItemBinding;
import com.xk.porject.home.WorkManageActivity;
import com.xk.porject.home.adaoter.CommonAdapter;
import com.xk.porject.home.adaoter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProjectPersonnelFragment extends Fragment {

    private ProjectPersonnelViewModel mViewModel;
    private FragmentProjectPersonnelBinding binding;
    public static ProjectPersonnelFragment newInstance() {
        return new ProjectPersonnelFragment();
    }
    private ProjectPersonnelAdapter commonAdapter;
    private List<ProjectPersonnel> list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProjectPersonnelBinding.inflate(getLayoutInflater());
        initdata();
        return binding.getRoot();
    }

    void initdata(){
        binding.tvTitleSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), WorkManageActivity.class);
                startActivity(intent);
            }
        });
        binding.rvProjectPersonnel.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCivilEngineeringPersonnel.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvContractorPersonnel.setLayoutManager(new LinearLayoutManager(getActivity()));
        list= new ArrayList<>();
        list.add(new ProjectPersonnel("王大风", "001", 58, 87, 45, 30, "在职", "无"));
        list.add(new ProjectPersonnel("李晓明", "002", 62, 92, 48, 28, "在职", "无"));
        list.add(new ProjectPersonnel("张力", "003", 55, 85, 50, 35, "在职", "警告"));
        list.add(new ProjectPersonnel("赵铁柱", "004", 60, 90, 47, 32, "在职", "无"));
        list.add(new ProjectPersonnel("孙小美", "005", 59, 88, 46, 33, "在职", "罚款"));
    commonAdapter = new ProjectPersonnelAdapter(list, new OnBindDataListener() {

        @Override
        public void onBindData(ProjectPersonnelAdapter.ViewHolder holder, int position, ProjectPersonnel personnel) {
            // 使用绑定实例访问视图
            holder.binding.tvName.setText(personnel.getName());
            holder.binding.tvNo.setText("编号: " + personnel.getNumber());
            holder.binding.tvLike.setText("信誉分: " + personnel.getCreditScore());
            holder.binding.tvSafe.setText("安全分: " + personnel.getSafetyScore());
            holder.binding.tvWork.setText("工勤: " + personnel.getDiligenceScore());
            holder.binding.tvCount.setText("计件: " + personnel.getPieceRateScore());
            holder.binding.tvStatus.setText("" + personnel.getStatus());
        }
    });
    binding.rvProjectPersonnel.setAdapter(commonAdapter);
        binding.rvCivilEngineeringPersonnel.setAdapter(commonAdapter);
        binding.rvContractorPersonnel.setAdapter(commonAdapter);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getActivity().onBackPressed();
            }
        });
    }


}