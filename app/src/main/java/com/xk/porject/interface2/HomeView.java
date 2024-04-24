package com.xk.porject.interface2;

import androidx.fragment.app.FragmentActivity;

import com.xk.porject.data.DataItem;
import com.xk.porject.home.adaoter.HomeBottomAdapterList;

import java.util.List;

public interface HomeView {
    void updateBottomList(List<HomeBottomAdapterList> list);
    void updateText(List<DataItem> list);

    FragmentActivity getMeActivity();
}
