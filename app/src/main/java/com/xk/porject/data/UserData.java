package com.xk.porject.data;

public class UserData {
    private int avatarResourceId; // 头像资源ID
    private String title; // 职位和姓名
    private String phoneNumber; // 电话号码
    private int projectIconResourceId; // 项目图标资源ID

    public UserData(int avatarResourceId, String title, String phoneNumber, int projectIconResourceId) {
        this.avatarResourceId = avatarResourceId;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.projectIconResourceId = projectIconResourceId;
    }

    public int getAvatarResourceId() {
        return avatarResourceId;
    }

    public void setAvatarResourceId(int avatarResourceId) {
        this.avatarResourceId = avatarResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getProjectIconResourceId() {
        return projectIconResourceId;
    }

    public void setProjectIconResourceId(int projectIconResourceId) {
        this.projectIconResourceId = projectIconResourceId;
    }
}