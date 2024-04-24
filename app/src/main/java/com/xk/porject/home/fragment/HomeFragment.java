package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.orhanobut.logger.Logger;
import com.xk.porject.R;
import com.xk.porject.data.DataItem;
import com.xk.porject.databinding.FragmentHomeBinding;
import com.xk.porject.home.AdvancedMaterialFormActivity;
import com.xk.porject.home.HomeViewModel;
import com.xk.porject.home.JobMarketActivity;
import com.xk.porject.home.MyCenterActivity;
import com.xk.porject.home.adaoter.HomeBottomAdapter;
import com.xk.porject.home.adaoter.HomeBottomAdapterList;
import com.xk.porject.home.adaoter.SlideshowAdapter;
import com.xk.porject.interface2.HomePresenter;
import com.xk.porject.interface2.HomeView;
import com.xk.porject.presenter.HomePresenterImpl;


import java.util.List;

public class HomeFragment extends Fragment implements HomeView {
    // ... 其他成员变量和方法 ...

    private HomePresenter presenter;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding fragmentHomeBinding;

    private HomeBottomAdapter homeBottomAdapter;

    private SlideshowAdapter slideshowAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // 初始化视图等...
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        presenter = new HomePresenterImpl(this, homeViewModel);
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        presenter.createData();
        presenter.subscribeForBottomListData();
        presenter.subscribeForTextData();
        fragmentHomeBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getMeActivity()));
        fragmentHomeBinding.imageViewHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyCenterActivity.class);
                startActivity(intent);
            }
        });
        fragmentHomeBinding.tvJobmarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), JobMarketActivity.class);
                startActivity(intent);
            }
        });
        fragmentHomeBinding.tvCommitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AdvancedMaterialFormActivity.class);
                startActivity(intent);
            }
        });

        // ...
        return fragmentHomeBinding.getRoot(); // 假设布局文件是fragment_home.xml
    }


    @Override
    public void updateBottomList(List<HomeBottomAdapterList> list) {
        Logger.d(list.toString());
        homeBottomAdapter =new HomeBottomAdapter(getActivity(),list);
        fragmentHomeBinding.recyclerView.setAdapter(homeBottomAdapter);
        homeBottomAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateText(List<DataItem> list) {
        slideshowAdapter =new SlideshowAdapter(getMeActivity(),list);
        fragmentHomeBinding.viewPagerHome.setAdapter(slideshowAdapter);
        slideshowAdapter.notifyDataSetChanged();
    }

    @Override
    public FragmentActivity getMeActivity() {
        return getActivity();
    }
}