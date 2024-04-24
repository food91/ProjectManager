package adminproject;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xk.adminproject.databinding.AdActivityMainBinding;
import com.xk.base.ui.BaseActivityPortrait;


import adminproject.adapter.MyPagerAdapter;

public class MainActivity extends BaseActivityPortrait<AdActivityMainBinding> {


    @Override
    protected void initData() {


    }

    @Override
    protected void onclick() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this);
        bind.viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(bind.tab, bind.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // 这里可以根据position设置Tab的标签
                switch (position) {
                    case 0:
                        tab.setText("按时间");
                        break;
                    case 1:
                        tab.setText("按开通数");
                        break;
                    case 2:
                        tab.setText("按未开通");
                        break;
                }
            }
        }).attach();
        bind.ivSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPortraitView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TabLayout.Tab tab1 = bind.tab.newTab();
        tab1.setText("按时间");
        bind.tab.addTab(tab1);
// 创建第二个选项卡并为其设置标题
        TabLayout.Tab tab2 =bind.tab.newTab();
        tab2.setText("按开通数");
        bind.tab.addTab(tab2);
        TabLayout.Tab tab3 =bind.tab.newTab();
        tab2.setText("按未开通");
        bind.tab.addTab(tab3);

    }
}