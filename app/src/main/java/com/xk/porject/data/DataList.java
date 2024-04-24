package com.xk.porject.data;

import java.util.List;

public class DataList<T> {

    @Override
    public String toString() {
        return "DataList{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public List<T> data;
    public   int errorCode;
    public  String errorMsg;
}
