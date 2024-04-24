package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xk.porject.R;
import com.xk.porject.databinding.FragmentPersonnelBinding;
import com.xk.porject.databinding.FragmentProjectPersonnelBinding;
import com.xk.porject.home.WorkManageActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonnelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonnelFragment extends Fragment {

    FragmentPersonnelBinding fragmentProjectPersonnelBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProjectPersonnelBinding=FragmentPersonnelBinding.inflate(getLayoutInflater());
        fragmentProjectPersonnelBinding.tvPersonManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WorkManageActivity.class);
                startActivity(intent);
            }
        });
        fragmentProjectPersonnelBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return fragmentProjectPersonnelBinding.getRoot();
    }
}