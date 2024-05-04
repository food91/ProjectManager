package com.xk.porject.contractor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.GroupInfo;
import com.xk.base.data.ResponseFindlist;
import com.xk.base.log.X;
import com.xk.base.ui.BaseFrament;
import com.xk.porject.R;
import com.xk.porject.adapter.TwoLevelAdapter;
import com.xk.porject.databinding.FragmentContractorManageBinding;
import com.xk.porject.databinding.ItemProjectlistBinding;
import com.xk.porject.viewmodel.ContractorManageViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContractorManageFragment extends BaseFrament {

    private FragmentContractorManageBinding binding;
    private ContractorManageViewModel contractorManageViewModel;
    private CommonAdapter<ItemProjectlistBinding, ResponseFindlist.Datum> adapterProjectlist;
    private TwoLevelAdapter adapterGrouplist;
    private List<ResponseFindlist.Datum> projectlistdata;
    private List<GroupInfo.Data> grouplistdata;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         contractorManageViewModel =
                new ViewModelProvider(this).get(ContractorManageViewModel.class);

        binding = FragmentContractorManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取NavController
                NavController navController = Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment_activity_contractor_main);
                // 执行导航动作
                navController.navigate(R.id.action_home_v3);
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取NavController
                NavController navController = Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment_activity_contractor_main);
                // 执行导航动作
                navController.navigate(R.id.action_home_v3);
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
        binding.rvGroup.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterGrouplist = new TwoLevelAdapter(new ArrayList<>());
        binding.rvProjectlist.setAdapter(adapterProjectlist);
        binding.rvGroup.setAdapter(adapterGrouplist);
        contractorManageViewModel.initdata();
        contractorManageViewModel.getProjectListLiveData().observe(getViewLifecycleOwner(), responseFindlist -> {
                // 更新适配器数据
                 projectlistdata = responseFindlist.getData();
            X.L(projectlistdata.toString());
                adapterProjectlist.setData(projectlistdata); // 假设getRows()返回Datum列表
        });
        contractorManageViewModel.getGroupListLiveData().observe(getViewLifecycleOwner(), GroupInfoListLiveData -> {
            // 更新适配器数据

            adapterProjectlist.setData(projectlistdata); // 假设getRows()返回Datum列表
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}