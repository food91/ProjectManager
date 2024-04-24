package com.xk.porject.data;
public class ProjectPersonnel {
    private String name;            // 项目经理的名字
    private String number;          // 编号
    private int creditScore;        // 信誉分
    private int safetyScore;        // 安全分
    private int diligenceScore;     // 工勤分
    private int pieceRateScore;     // 计件分
    private String status;          // 在职状态
    private String punishment;      // 技工处罚

    // 构造器
    public ProjectPersonnel(String name, String number, int creditScore, int safetyScore,
                            int diligenceScore, int pieceRateScore, String status, String punishment) {
        this.name = name;
        this.number = number;
        this.creditScore = creditScore;
        this.safetyScore = safetyScore;
        this.diligenceScore = diligenceScore;
        this.pieceRateScore = pieceRateScore;
        this.status = status;
        this.punishment = punishment;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getSafetyScore() {
        return safetyScore;
    }

    public void setSafetyScore(int safetyScore) {
        this.safetyScore = safetyScore;
    }

    public int getDiligenceScore() {
        return diligenceScore;
    }

    public void setDiligenceScore(int diligenceScore) {
        this.diligenceScore = diligenceScore;
    }

    public int getPieceRateScore() {
        return pieceRateScore;
    }

    public void setPieceRateScore(int pieceRateScore) {
        this.pieceRateScore = pieceRateScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }
}
