package com.xk.porject.home;

import android.text.TextUtils;
import android.view.View;


import com.kongzue.dialogx.dialogs.PopTip;
import com.xk.base.data.Response;
import com.xk.base.data.StockData;
import com.xk.base.net.ApiClient;
import com.xk.base.net.ApiService;
import com.xk.base.ui.BaseActivityPortrait;
import com.xk.porject.databinding.ActivityOutInButBinding;

import io.reactivex.functions.Consumer;

public class OutInButActivity extends BaseActivityPortrait<ActivityOutInButBinding> {

    @Override
    protected void initData() {

    }

    @Override
    protected void onclick() {
        bind.backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bind.tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addStock(){
        String name = bind.edName.getText().toString();
        String size = bind.edSize.getText().toString();
        String type = bind.edType.getText().toString();
        String material_num = bind.edShopNum.getText().toString();
        String unit = bind.edPrice.getText().toString();
        String num =bind.edAddnum.getText().toString();
        if(TextUtils.isEmpty(num)||TextUtils.isEmpty(size)||TextUtils.isEmpty(type)||TextUtils.isEmpty(material_num)
        ||TextUtils.isEmpty(unit)||TextUtils.isEmpty(name)){
            PopTip.show("输入不能为空");
            return;
        }
        StockData stockData = new StockData();
        stockData.setNumber(Integer.parseInt(material_num));
        stockData.setMaterialType(type);
        stockData.setMaterialName(name);
        stockData.setMaterialSpecification(Integer.parseInt(size));
        stockData.setNumber(Integer.parseInt(num));
        performApiCall(ApiClient.getClient().create(ApiService.class).addStock(stockData), new Consumer<Response>() {
            @Override
            public void accept(Response response) throws Exception {
                if(response.getCode()==200){

                }else{
                    PopTip.show(response.getMsg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    protected void initPortraitView() {

    }
}