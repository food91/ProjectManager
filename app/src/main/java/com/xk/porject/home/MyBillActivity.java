package com.xk.porject.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;


import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.data.Transaction;
import com.xk.porject.databinding.ActivityMyBillBinding;
import com.xk.porject.databinding.TransactionItemBinding;
import com.xk.porject.home.adaoter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyBillActivity extends BaseActivityPortrait<ActivityMyBillBinding> {

    private CommonAdapter<TransactionItemBinding, Transaction> commonAdapter;
    private List<Transaction> transactions ;

    private void addData(){
         transactions = new ArrayList<>();

        transactions.add(new Transaction("2024-04-07", "提现", "-100元", "余额 3200.00元"));
        transactions.add(new Transaction("2024-04-06", "工资", "+5000元", "余额 3300.00元"));
        transactions.add(new Transaction("2024-04-05", "购物", "-200元", "余额 1800.00元"));
        transactions.add(new Transaction("2024-04-04", "转账", "-500元", "余额 2000.00元"));
        transactions.add(new Transaction("2024-04-03", "存款", "+1500元", "余额 2500.00元"));
        transactions.add(new Transaction("2024-04-02", "充值", "-50元", "余额 1000.00元"));
        transactions.add(new Transaction("2024-04-01", "还款", "-300元", "余额 1050.00元"));

    }

    @Override
    protected void initData() {
        addData();
    }

    @Override
    protected void onclick() {
    commonAdapter=new CommonAdapter<TransactionItemBinding, Transaction>(transactions) {
        @Override
        protected void show(TransactionItemBinding holder, int position, Transaction transaction) {
            holder.tvDate.setText(transaction.getDate());
            holder.tvEvent.setText(transaction.getEvent());
            holder.tvAmount.setText(transaction.getAmount());
            holder.tvBalance.setText(transaction.getBalance());
        }
    };
    bind.transactionsListView.setAdapter(commonAdapter);
    }

    @Override
    protected void initPortraitView() {
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.transactionsListView.setLayoutManager(new LinearLayoutManager(this));
    }
}