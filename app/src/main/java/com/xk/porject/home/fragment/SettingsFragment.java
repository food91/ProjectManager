package com.xk.porject.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;
import com.tencent.mmkv.MMKV;
import com.xk.base.ui.BaseFrament;
import com.xk.porject.R;
import com.xk.porject.databinding.FragmentSettingsBinding;
import com.xk.porject.home.ChangePWActivity;
import com.xk.porject.home.ChangePhoneActivity;
import com.xk.porject.home.FeedbackActivity;
import com.xk.porject.home.OutInActivity;
import com.xk.porject.home.ProgressActivity;
import com.xk.porject.home.SafetyActivity;
import com.xk.porject.home.SwitchAccountsActivity;
import com.xk.porject.home.VersionActivity;
import com.xk.porject.home.WorkCardActivity;
import com.xk.porject.ui.LoginActivity;


public class SettingsFragment extends BaseFrament {

    FragmentSettingsBinding bind;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = FragmentSettingsBinding.inflate(getLayoutInflater());
        init();
        return bind.getRoot();
    }

    private  void  init(){
        bind.tvProgressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ProgressActivity.class);
                startActivity(intent);
            }
        });
        bind.tvInbound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), OutInActivity.class);
                startActivity(intent);
            }
        });
        bind.tvChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ChangePhoneActivity.class);
                startActivity(intent);
            }
        });
        bind.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomMenu.build().setMenuList(new String[]{"切换账号","退出登陆"})
                        .setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() {
                            @Override
                            public boolean onClick(BottomMenu dialog, CharSequence text, int index) {
                               if(index==0){
                                   Intent intent =new Intent(getActivity(), SwitchAccountsActivity.class);
                                   startActivity(intent);
                               }else if(index==1){
                                   Intent intent =new Intent(getActivity(), LoginActivity.class);
                                   startActivity(intent);
                                   MMKV.defaultMMKV().putString("token","");
                                   getActivity().finish();
                               }
                                return false;
                            }
                        }).show();
            }
        });
        bind.tvSecurityQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SafetyActivity.class);
                startActivity(intent);
            }
        });
        bind.tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), ChangePWActivity.class);
                startActivity(intent);
            }
        });
        bind.tvWorkCardSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), WorkCardActivity.class);
                startActivity(intent);
            }
        });
        bind.tvCheckForUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), VersionActivity.class);
                startActivity(intent);
            }
        });
        bind.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        bind.tvFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}