package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xk.base.adapter.CommonAdapter;
import com.xk.base.data.DictCode;
import com.xk.base.data.ResponseFindProjectList;
import com.xk.base.ui.BaseFrament;
import com.xk.porject.adapter.MoreLVAdapter;
import com.xk.porject.databinding.FragmentPersonnelBinding;
import com.xk.porject.databinding.ItemRpojectWorkerBinding;
import com.xk.porject.projectmain.ProjectWorkManageActivity;
import com.xk.porject.viewmodel.ManageViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonnelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonnelFragment extends BaseFrament {

    FragmentPersonnelBinding fragmentProjectPersonnelBinding;
    private int projectid;
    private ManageViewModel viewModel;
    ResponseFindProjectList.Data list;
    private MoreLVAdapter moreLVAdapter;
    private CommonAdapter<ItemRpojectWorkerBinding, ResponseFindProjectList.Data.Project> commonAdapter;

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
    List<HashMap<String,String>> hashMapList;
    private List<String> spinner_string;

    private void setSpinner(List<DictCode.Data> dataList) {
        spinner_string = new ArrayList<>();
        hashMapList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(dataList.get(i).getDictCode() + "", dataList.get(i).getDictLabel());
            hashMapList.add(hashMap);
            spinner_string.add(dataList.get(i).getDictLabel());
        }
    }

    private void init() {
        projectid = viewModel.search;
        viewModel.getGroupInfo().observe(getActivity(), new Observer<ResponseFindProjectList.Data>() {
            @Override
            public void onChanged(ResponseFindProjectList.Data data) {
                viewModel.getWorkState();
                list=data;
            }
        });
        viewModel.workStateString.observe(getActivity(), new Observer<List<DictCode.Data>>() {
            @Override
            public void onChanged(List<DictCode.Data> data) {
                setSpinner(data);
            }
        });
        viewModel.getData(projectid);
    }
}