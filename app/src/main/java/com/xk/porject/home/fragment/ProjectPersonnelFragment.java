package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.xk.base.data.GroupInfo;
import com.xk.base.log.X;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseFrament;
import com.xk.porject.adapter.OnBindDataListener;
import com.xk.porject.adapter.ProjectPersonnelAdapter;
import com.xk.porject.data.ProjectPersonnel;
import com.xk.porject.databinding.FragmentProjectPersonnelBinding;
import com.xk.porject.home.WorkManageActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectPersonnelFragment extends BaseFrament {

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
        onclick();
        getProjectPersonData();
        return binding.getRoot();
    }

    private void getProjectPersonData(){
        ApiClient.getClient().create(ApiService.class)
                .getgroup("0").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        WaitDialog.show("加载中");
                    }

                    @Override
                    public void onNext(GroupInfo response) {
                        WaitDialog.dismiss();
                        X.L(response.toString());
                        if(response.getCode()==200){

                        }else{
                            PopTip.show(response.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        WaitDialog.dismiss();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void onclick(){
        binding.llProjectManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rvProjectPersonnel.getVisibility()==View.VISIBLE){
                    binding.rvProjectPersonnel.setVisibility(View.GONE);
            }else{
                binding.rvProjectPersonnel.setVisibility(View.VISIBLE);
            }
            }
        });
        binding.llContractorManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.rvContractorPersonnel.getVisibility()==View.VISIBLE){
                    binding.rvContractorPersonnel.setVisibility(View.GONE);
            }else{
                binding.rvContractorPersonnel.setVisibility(View.VISIBLE);
            }
                }
        });
        binding.llCivilEngineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.rvCivilEngineeringPersonnel.getVisibility() == View.VISIBLE) {
                    binding.rvCivilEngineeringPersonnel.setVisibility(View.GONE);
                } else {
                    binding.rvCivilEngineeringPersonnel.setVisibility(View.VISIBLE);
                }
            }
        });
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