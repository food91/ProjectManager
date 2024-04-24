package com.xk.porject.data;


public class WorkDayData {

    int data;
    int weekday;

    int day;

    public WorkDayData(int data, int weekday, int day) {
        this.data = data;
        this.weekday = weekday;
        this.day = day;
    }

    public int getData() {
        return data;
    }
    public String getDataString() {
        if(data==-1){
            return "";
        }
        return data+"";
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getDay() {
        return day;
    }

    public String getDayString(){
        if(day==-1){
            return "";
        }
        return day+"";
    }


    public void setDay(int day) {
        this.day = day;
    }
}
