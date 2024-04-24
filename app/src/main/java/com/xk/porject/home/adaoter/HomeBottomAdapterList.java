package com.xk.porject.home.adaoter;

public class HomeBottomAdapterList {
    private String title;
    private String text;
    private String buttontext; // 假设图标是Drawable资源

    @Override
    public String toString() {
        return "HomeBottomAdapterList{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", buttontext='" + buttontext + '\'' +
                '}';
    }

    public String getButtontext() {
        return buttontext;
    }

    public void setButtontext(String buttontext) {
        this.buttontext = buttontext;
    }

    public HomeBottomAdapterList(String title, String text, String buttontext) {
        this.title = title;
        this.text = text;
        this.buttontext = buttontext;
    }

    // Getter和Setter方法
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




}