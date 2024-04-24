package com.xk.porject.data;

public class QuestionItem {
    private String questionName;
    private String editStatus;
    private String publishStatus;
    private String draftStatus;
    private String date;
    private int option1SelectedIndex;
    private int option2SelectedIndex;
    private int option3SelectedIndex;
    private int option4SelectedIndex;

    // Constructor
    public QuestionItem(String questionName, String editStatus, String publishStatus, String draftStatus, String date) {
        this.questionName = questionName;
        this.editStatus = editStatus;
        this.publishStatus = publishStatus;
        this.draftStatus = draftStatus;
        this.date = date;
        this.option1SelectedIndex = 0; // Default selected index
        this.option2SelectedIndex = 0;
        this.option3SelectedIndex = 0;
        this.option4SelectedIndex = 0;
    }

    // Getters and Setters
    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(String editStatus) {
        this.editStatus = editStatus;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(String draftStatus) {
        this.draftStatus = draftStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOption1SelectedIndex() {
        return option1SelectedIndex;
    }

    public void setOption1SelectedIndex(int option1SelectedIndex) {
        this.option1SelectedIndex = option1SelectedIndex;
    }

    public int getOption2SelectedIndex() {
        return option2SelectedIndex;
    }

    public void setOption2SelectedIndex(int option2SelectedIndex) {
        this.option2SelectedIndex = option2SelectedIndex;
    }

    public int getOption3SelectedIndex() {
        return option3SelectedIndex;
    }

    public void setOption3SelectedIndex(int option3SelectedIndex) {
        this.option3SelectedIndex = option3SelectedIndex;
    }

    public int getOption4SelectedIndex() {
        return option4SelectedIndex;
    }

    public void setOption4SelectedIndex(int option4SelectedIndex) {
        this.option4SelectedIndex = option4SelectedIndex;
    }
}

