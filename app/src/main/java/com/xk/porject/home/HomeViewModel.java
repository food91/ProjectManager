package com.xk.porject.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.xk.porject.data.DataItem;
import com.xk.porject.data.DataList;
import com.xk.porject.home.adaoter.HomeBottomAdapterList;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;



import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<DataItem>> image = new MutableLiveData<>();
    private MutableLiveData<List<HomeBottomAdapterList>> bottomList = new MutableLiveData<>();


    public HomeViewModel() {
        init();
    }

    private void init() {
        // Assuming ApiClient and ApiService are already implemented in Java

    }

    public void createData() {
        List<HomeBottomAdapterList> data = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            switch (i) {
                case 0:
                    data.add(new HomeBottomAdapterList("我的工勤", "按日为单位发放工资信息基础", "记工"));
                    break;
                case 1:
                    data.add(new HomeBottomAdapterList("我的计件", "按件为单位发放工资详细数据", ""));
                    break;
                case 2:
                    data.add(new HomeBottomAdapterList("我的工资", "按件为单位发放工资详细数据", ""));
                    break;
                case 3:
                    data.add(new HomeBottomAdapterList("账单", "历史账单查询，扣罚神情等数据", ""));
                    break;
                case 4:
                    data.add(new HomeBottomAdapterList("工单/进度", "工作联系单/工作进入表", ""));
                    break;
                case 5:
                    data.add(new HomeBottomAdapterList("我的考勤", "定位/签到", "签到 签退"));
                    break;
                // Add other cases similarly...
                default:
                    break;
            }
        }
        bottomList.postValue(data);
    }

    public MutableLiveData<List<HomeBottomAdapterList>> getBottomList() {
        return bottomList;
    }

    public MutableLiveData<List<DataItem>> getText() {
        return image;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Dispose all ongoing operations when ViewModel is cleared

    }
}