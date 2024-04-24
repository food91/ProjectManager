package adminproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.adminproject.databinding.AdFragmentTimeBinding;

import java.util.ArrayList;
import java.util.List;

import adminproject.adapter.MyViewPagerAdapter;
import adminproject.data.ProjectInfo;

public class TimeFragment extends Fragment {
    private MyViewPagerAdapter myViewPagerAdapter;
    AdFragmentTimeBinding fragmentTimeBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTimeBinding = AdFragmentTimeBinding.inflate(getLayoutInflater());
        init();
        return fragmentTimeBinding.getRoot();
    }

    private void init(){
            List<ProjectInfo> sampleData = new ArrayList<>();
            sampleData.add(new ProjectInfo("内蒙古金兴机械 涡轮设计生产项目", "编号：", "MM025635", "注册时间：", "2015-02-08", "公司 3 开通 0"));
            sampleData.add(new ProjectInfo("上海轨道交通 设备维护项目", "编号：", "SH200045", "注册时间：", "2018-06-15", "公司 10 开通 5"));
            sampleData.add(new ProjectInfo("广东电信 5G网络扩展项目", "编号：", "GD989003", "注册时间：", "2020-11-20", "公司 8 开通 8"));
            myViewPagerAdapter = new MyViewPagerAdapter(sampleData,getActivity());
            fragmentTimeBinding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            fragmentTimeBinding.rv.setAdapter(myViewPagerAdapter);
    }
}