package com.xk.porject.home.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xk.base.adapter.CommonAdapter;
import com.xk.porject.data.TableItem;
import com.xk.porject.databinding.FragmentMoneyBinding;
import com.xk.porject.databinding.FragmentMoneyListBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 */
public class MoneyFragment extends Fragment {

    FragmentMoneyBinding bind;

    List<TableItem> list;
    CommonAdapter<FragmentMoneyListBinding,TableItem> commonAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = FragmentMoneyBinding.inflate(getLayoutInflater());
        initData();
        return bind.getRoot();
    }
    private void initData(){
        list = new ArrayList<>();
        list.add(new TableItem("1月", 10000.0, 5000.0, 2000.0, 3000.0, "填写"));
        list.add(new TableItem("2月", 15000.0, 5500.0, 2500.0, 3500.0, "合集"));
        list.add(new TableItem("3月", 20000.0, 6000.0, 3000.0, 4000.0, "填写"));
        bind.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonAdapter =new CommonAdapter<FragmentMoneyListBinding, TableItem>(list) {
            @Override
            protected void show(FragmentMoneyListBinding holder, int position, TableItem tableItem) {
                if(position==0){
                    return;
                }
                position--;
                // 设置月份
                holder.tvMonthHeader.setText(tableItem.getMonth());
                // 设置工程款
                holder.tvProjectFundsHeader.setText(String.format(Locale.getDefault(), "%.2f", tableItem.getProjectFunds()));
                // 设置人民工资
                holder.tvSalaryHeader.setText(String.format(Locale.getDefault(), "%.2f", tableItem.getSalary()));
                // 设置代教费
                holder.tvSubstituteFeeHeader.setText(String.format(Locale.getDefault(), "%.2f", tableItem.getSubstituteFee()));
                // 设置结欠
                holder.tvDebtHeader.setText(String.format(Locale.getDefault(), "%.2f", tableItem.getDebt()));
                // 设置填写/合集
                holder.tvEntryCollectionHeader.setText(tableItem.getEntryCollection());
            }
        };
        bind.rv.setAdapter(commonAdapter);
    }
}