package com.xk.porject.data;

public class AnData {
    String title;
    String text;

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

    public AnData(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
