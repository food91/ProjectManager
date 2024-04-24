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

import com.xk.civilengineering.home.vm.DashboardViewModel;
import com.xk.porject.R;
import com.xk.porject.databinding.FragmentManageBinding;
import com.xk.porject.databinding.FragmentProjectmanageBinding;
import com.xk.porject.home.AddContractorActivity;
import com.xk.porject.home.CreateProjectActivity;

public class ProjectManageFragment extends Fragment {

    private FragmentProjectmanageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}