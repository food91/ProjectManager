package com.xk.porject.data;

public class EmployeeEvaluation {
    private boolean fineWorkmanship;
    private boolean serious;
    private int score;
    private String position;

    // Constructor
    public EmployeeEvaluation(boolean fineWorkmanship, boolean serious, int score, String position) {
        this.fineWorkmanship = fineWorkmanship;
        this.serious = serious;
        this.score = score;
        this.position = position;
    }

    // Getters and Setters
    public boolean isFineWorkmanship() {
        return fineWorkmanship;
    }

    public void setFineWorkmanship(boolean fineWorkmanship) {
        this.fineWorkmanship = fineWorkmanship;
    }

    public boolean isSerious() {
        return serious;
    }

    public void setSerious(boolean serious) {
        this.serious = serious;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "EmployeeEvaluation{" +
                "fineWorkmanship=" + fineWorkmanship +
                ", serious=" + serious +
                ", score=" + score +
                ", position='" + position + '\'' +
                '}';
    }
}
