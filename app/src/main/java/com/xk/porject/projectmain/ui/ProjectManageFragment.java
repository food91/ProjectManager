package com.xk.porject.projectmain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.log.X;
import com.xk.base.ui.BaseFrament;
import com.xk.civilengineering.home.vm.DashboardViewModel;
import com.xk.porject.R;
import com.xk.porject.adapter.TwoLevelAdapter;
import com.xk.porject.databinding.FragmentManageBinding;
import com.xk.porject.databinding.FragmentProjectmanageBinding;
import com.xk.porject.databinding.ItemProjectlistBinding;
import com.xk.porject.home.AddContractorActivity;
import com.xk.porject.home.CreateProjectActivity;
import com.xk.porject.viewmodel.ContractorManageViewModel;
import com.xk.porject.viewmodel.ProjectManageViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectManageFragment extends BaseFrament {

    private FragmentProjectmanageBinding binding;
    private CommonAdapter<ItemProjectlistBinding, ResponseFindlist.Datum> adapterProjectlist;
    private List<ResponseFindlist.Datum> projectlistdata;
    private ProjectManageViewModel contractorManageViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contractorManageViewModel =
                new ViewModelProvider(this).get(ProjectManageViewModel.class);

        binding = FragmentProjectmanageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取NavController
                NavController navController = Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment_activity_porject_app_main);
                // 执行导航动作
                navController.navigate(R.id.action_startFragment_to_projectPersonnelFragment);
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
                startActivity(intent);
            }
        });
        initdata();
        return root;
    }

    private void initdata(){
        binding.rvProjectlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterProjectlist = new CommonAdapter<ItemProjectlistBinding, ResponseFindlist.Datum>(new ArrayList<>()) {
            @Override
            protected void show(ItemProjectlistBinding holder, int position, ResponseFindlist.Datum responseFindlist) {
                holder.tvName.setText(responseFindlist.getProjectName());
            }
        };
        binding.rvProjectlist.setAdapter(adapterProjectlist);
        contractorManageViewModel.initdata();
        contractorManageViewModel.getProjectListLiveData().observe(getViewLifecycleOwner(), responseFindlist -> {
            // 更新适配器数据
            projectlistdata = responseFindlist.getData();
            adapterProjectlist.setData(projectlistdata); // 假设getRows()返回Datum列表
            if(!projectlistdata.isEmpty()){
                contractorManageViewModel.getContractorList(projectlistdata.get(0).getid());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}