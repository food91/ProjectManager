package com.xk.porject.contractor.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.xk.base.ui.BaseFrament;
import com.xk.civilengineering.home.vm.DashboardViewModel;
import com.xk.porject.R;
import com.xk.porject.databinding.FragmentContractorManageBinding;
import com.xk.porject.databinding.FragmentManageBinding;

public class ContractorManageFragment extends BaseFrament {

    private FragmentContractorManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}