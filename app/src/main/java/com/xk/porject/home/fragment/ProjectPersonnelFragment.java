package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kongzue.dialogx.datepicker.CalendarDialog;
import com.kongzue.dialogx.datepicker.interfaces.OnDateSelected;
import com.kongzue.dialogx.dialogs.CustomDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnBindView;
import com.tencent.mmkv.MMKV;
import com.xk.base.data.DictCode;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.ResponseAllWorkData;
import com.xk.base.data.WageRequest;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseFrament;
import com.xk.base.ui.XTextView;
import com.xk.porject.R;
import com.xk.porject.adapter.OnBindDataListener;
import com.xk.porject.adapter.ProjectPersonnelAdapter;
import com.xk.porject.data.ProjectPersonnel;
import com.xk.porject.databinding.FragmentProjectPersonnelBinding;
import com.xk.porject.home.WorkManageActivity;
import com.xk.porject.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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

    private List<ResponseAllWorkData.Data.Project> projectList;

    private List<ProjectPersonnel> list;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentProjectPersonnelBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(this).get(ProjectPersonnelViewModel.class);
        initdata();
        onclick();
        return binding.getRoot();
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
        mViewModel.init();
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
    commonAdapter = new ProjectPersonnelAdapter(new ArrayList<>(), new OnBindDataListener() {
        @Override
        public void onBindData(ProjectPersonnelAdapter.ViewHolder holder, int position, ResponseAllWorkData.Data.Project personnel) {
            holder.binding.tvName.setText(personnel.getEbJob() + "-" + personnel.getName());
            holder.binding.tvNo.setText("编号: " + personnel.getEmployId());
            holder.binding.tvLike.setText("信誉分 :" + personnel.getReputation_score());
            Glide.with(getActivity()).load(personnel.getImg()).into(holder.binding.ivImg);
            holder.binding.tvSafe.setText("安全分: " + personnel.getSafety_score());
            holder.binding.tvWork.setText("工勤: " + personnel.getWork_service());
            holder.binding.tvWage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        CustomDialog.show(new OnBindView<CustomDialog>(R.layout.dialog_project_wage) {
                        @Override
                        public void onBind(final CustomDialog dialog, View v) {
                            XTextView btnOk;
                            TextView tv_time;
                            EditText ed_wage,ed_count,ed_punish,ed_addwork,ed_extrm;
                            btnOk = v.findViewById(R.id.tv_send);
                            tv_time = v.findViewById(R.id.tvsubmitTime);
                            ed_wage = v.findViewById(R.id.ed_wage);
                            ed_count = v.findViewById(R.id.ed_count);
                            ed_punish = v.findViewById(R.id.ed_punish);
                            ed_addwork = v.findViewById(R.id.ed_add_jobtime);
                            ed_extrm = v.findViewById(R.id.ed_extrm);
                            tv_time.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CalendarDialog.build().show(new OnDateSelected() {
                                        @Override
                                        public void onSelect(String s, int i, int i1, int i2) {
                                            tv_time.setText(s);
                                        }
                                    });
                                }
                            });
                            btnOk.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String str_time = tv_time.getText().toString();
                                    String str_wage = ed_wage.getText().toString();
                                    String str_count = ed_count.getText().toString();
                                    String str_punish = ed_punish.getText().toString();
                                    String str_addwork = ed_addwork.getText().toString();
                                    String str_extrm = ed_extrm.getText().toString();
                                    if(!Utils.areAllStringsValid(str_time,str_wage,str_count,str_punish,str_addwork,str_extrm)){
                                        PopTip.show("输入不能有空");
                                        return;
                                    }
                                    dialog.dismiss();
                                }
                            });
                        }
                    }).setAlign(CustomDialog.ALIGN.CENTER);
                }
            });
        }
    });
        binding.rvProjectPersonnel.setAdapter(commonAdapter);
        binding.rvCivilEngineeringPersonnel.setAdapter(commonAdapter);
        binding.rvContractorPersonnel.setAdapter(commonAdapter);
        mViewModel.getDataInfo().observe(getActivity(), new Observer<List<ResponseAllWorkData.Data.Project>>() {
            @Override
            public void onChanged(List<ResponseAllWorkData.Data.Project> projects) {
                projectList = projects;
                commonAdapter.setData(projectList);
                binding.tvProjectTitle.setText("项目方管理组("+projects.size()+"人)");
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


}