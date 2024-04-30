package com.xk.porject.teamleader.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xk.porject.databinding.ActivityContractorLeaderBinding;
import com.xk.porject.databinding.FragmentPersonnelBinding;
import com.xk.porject.home.WorkManageActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearderManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearderManagerFragment extends Fragment {

    ActivityContractorLeaderBinding fragmentProjectPersonnelBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProjectPersonnelBinding=ActivityContractorLeaderBinding.inflate(getLayoutInflater());
        return fragmentProjectPersonnelBinding.getRoot();
    }
}