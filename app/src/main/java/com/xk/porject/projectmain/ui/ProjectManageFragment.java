package com.xk.porject.projectmain.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.ResponseContractList;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.log.X;
import com.xk.base.ui.BaseFrament;
import com.xk.porject.R;
import com.xk.porject.databinding.FragmentProjectmanageBinding;
import com.xk.porject.databinding.ItemProjectlistBinding;
import com.xk.porject.home.AddContractorActivity;
import com.xk.porject.home.CreateProjectActivity;
import com.xk.porject.viewmodel.ManageViewModel;
import com.xk.porject.viewmodel.ProjectManageViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectManageFragment extends BaseFrament {

    private FragmentProjectmanageBinding binding;
    private CommonAdapter<ItemProjectlistBinding, ResponseFindlist.Datum> adapterProjectlist;
    private CommonAdapter<ItemProjectlistBinding,String> adapterContract;
    private List<ResponseFindlist.Datum> projectlistdata;
    private List<ResponseContractList.Datum> contractlistdata=new ArrayList<>();
    private ProjectManageViewModel contractorManageViewModel;
    private int clickPosition=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contractorManageViewModel =
                new ViewModelProvider(this).get(ProjectManageViewModel.class);

        binding = FragmentProjectmanageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取NavController
                nagation();
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nagation();
            }
        });
        binding.btAddproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), CreateProjectActivity.class);
                startActivity(intent);
            }
        });
        binding.btContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddContractorActivity.class);
                ArrayList<String> project = new ArrayList<>();
                for(int i=0;i<projectlistdata.size();i++){
                    project.add(projectlistdata.get(i).getProjectName());
                }
                ArrayList<String> contractname = new ArrayList<>();
                ArrayList<String> contractphone = new ArrayList<>();
                for(int i=0;i<contractlistdata.size();i++){
                    contractname.add(contractlistdata.get(i).getContractName());
                    contractphone.add(contractlistdata.get(i).getContractNumber());
                }
                Set<String> set = new HashSet<>(contractphone);

                ArrayList<String> uniquePhoneNumbers = new ArrayList<>(set);
                intent.putStringArrayListExtra("project",project);
                intent.putStringArrayListExtra("cname",contractname);
                intent.putStringArrayListExtra("cphone",uniquePhoneNumbers);
                startActivity(intent);
            }
        });
        binding.ivSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = binding.etSearch.getText().toString();
                if(TextUtils.isEmpty(search)){
                    PopTip.show("请输入搜索项目名称");
                    return;
                }
                for(int i=0;i<projectlistdata.size();i++){
                    if(projectlistdata.get(i).getProjectName().equals(search)){
                        clickPosition = i;
                        adapterProjectlist.notifyDataSetChanged();
                        updateContractlist();
                        return;
                    }
                }
                PopTip.show("没有搜索到项目，请确认项目名称");
            }
        });
        initdata();
        return root;
    }

    private void nagation(){
        NavController navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment_activity_porject_app_main);
        ManageViewModel viewModel = new ViewModelProvider(getActivity()).get(ManageViewModel.class);
         viewModel.search = projectlistdata.get(clickPosition).getid();
        navController.navigate(R.id.action_startFragment_to_projectPersonnelFragment);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initdata(){
        binding.rvProjectlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterProjectlist = new CommonAdapter<ItemProjectlistBinding, ResponseFindlist.Datum>(new ArrayList<>()) {
            @Override
            protected void show(ItemProjectlistBinding holder, int position, ResponseFindlist.Datum responseFindlist) {
                holder.tvName.setText(responseFindlist.getProjectName());
                if (clickPosition == position) {
                    // 将"#f2f2f2"转换为整数
                    holder.main.setBackgroundColor(Color.parseColor("#666666"));
                } else {
                    // 将"#666666"转换为整数
                    holder.main.setBackgroundColor(Color.parseColor("#f2f2f2"));
                }
                holder.main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = position;
                        notifyDataSetChanged();
                        updateContractlist();
                    }
                });
            }
        };
        adapterContract = new CommonAdapter<ItemProjectlistBinding, String>(new ArrayList<>()) {
            @Override
            protected void show(ItemProjectlistBinding holder, int position, String responseFindlist) {
                    holder.tvName.setText(responseFindlist);
            }
        };
        binding.rvProjectlist.setAdapter(adapterProjectlist);
        binding.rv.setAdapter(adapterContract);
        contractorManageViewModel.initdata();
        contractorManageViewModel.getProjectListLiveData().observe(getViewLifecycleOwner(), responseFindlist -> {
            // 更新适配器数据
            projectlistdata = responseFindlist.getData();
            adapterProjectlist.setData(projectlistdata); // 假设getRows()返回Datum列表
        });
        contractorManageViewModel.getContractListLiveData().observe(getViewLifecycleOwner(), responseContractList -> {
            // 更新适配器数据
            contractlistdata = responseContractList.getData();
           updateContractlist();
        });

    }

    private void updateContractlist(){
        ArrayList<String> list = new ArrayList<>();
        if(contractlistdata.isEmpty()){
            return;
        }

        for(int i=0;i<contractlistdata.size();i++){
            if(contractlistdata.get(i).getProjectName().equals(projectlistdata.get(clickPosition).getProjectName())){
                list.add(contractlistdata.get(i).getContractName());
            }
        }
        adapterContract.setData(list);
    }

    private void delete(){
        for(int i=0;i<contractlistdata.size();i++){
            contractorManageViewModel.delete(contractlistdata.get(i).getid()+"");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}