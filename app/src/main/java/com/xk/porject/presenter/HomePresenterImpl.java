package com.xk.porject.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.xk.porject.data.DataItem;
import com.xk.porject.home.HomeViewModel;
import com.xk.porject.home.adaoter.HomeBottomAdapterList;
import com.xk.porject.interface2.HomePresenter;
import com.xk.porject.interface2.HomeView;


import java.util.List;

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;
    private HomeViewModel homeViewModel;

    public HomePresenterImpl(HomeView homeView, HomeViewModel homeViewModel) {
        this.homeView = homeView;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void createData() {
        homeViewModel.createData();
    }

    @Override
    public void subscribeForBottomListData() {
        LiveData<List<HomeBottomAdapterList>> bottomListData = homeViewModel.getBottomList();
        bottomListData.observe(homeView.getMeActivity(), new Observer<List<HomeBottomAdapterList>>() {
            @Override
            public void onChanged(List<HomeBottomAdapterList> homeBottomAdapterLists) {
                homeView.updateBottomList(homeBottomAdapterLists);
            }
        });
    }

    @Override
    public void subscribeForTextData() {
        LiveData<List<DataItem>>bottomListData = homeViewModel.getText();
        bottomListData.observe(homeView.getMeActivity(), new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                homeView.updateText(dataItems);
            }
        });
    }
}
