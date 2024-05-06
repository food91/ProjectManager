package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xk.base.log.X;
import com.xk.porject.R;
import com.xk.porject.databinding.FragmentPersonnelBinding;
import com.xk.porject.databinding.FragmentProjectPersonnelBinding;
import com.xk.porject.home.WorkManageActivity;
import com.xk.porject.projectmain.ProjectWorkManageActivity;
import com.xk.porject.viewmodel.ManageViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonnelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonnelFragment extends Fragment {

    FragmentPersonnelBinding fragmentProjectPersonnelBinding;
    private int projectid;
    private ManageViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProjectPersonnelBinding=FragmentPersonnelBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(getActivity()).get(ManageViewModel.class);
        fragmentProjectPersonnelBinding.tvPersonManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ProjectWorkManageActivity.class);
                intent.putExtra("id",projectid);
                intent.putExtra("projectname",viewModel.projectname);
                startActivity(intent);
            }
        });
        fragmentProjectPersonnelBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        init();
        return fragmentProjectPersonnelBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void init(){
          projectid = viewModel.search;
          viewModel.getData(projectid);
    }
}