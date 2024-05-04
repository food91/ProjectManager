package com.xk.base.ui.excel;

import java.util.List;

public class Data {

    private String text;
    private String ActualProgress;
    private String AllProgress;
    private int Actualstart;
    private int Actualend;

    private List<String> rows;


    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActualProgress() {
        return ActualProgress;
    }

    public void setActualProgress(String actualProgress) {
        ActualProgress = actualProgress;
    }

    public String getAllProgress() {
        return AllProgress;
    }

    public void setAllProgress(String allProgress) {
        AllProgress = allProgress;
    }

    public int getActualstart() {
        return Actualstart;
    }

    public void setActualstart(int actualstart) {
        Actualstart = actualstart;
    }

    public int getActualend() {
        return Actualend;
    }

    public void setActualend(int actualend) {
        Actualend = actualend;
    }
}
