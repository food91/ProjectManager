package com.xk.porject.home.data;

public class MaterialInfo {
    private String submitTime; // 提交时间
    private String approvalTime; // 批准时间
    private String name; // 名称
    private String model; // 型号
    private String specification; // 规格
    private int quantity; // 数量
    private String usage; // 用途
    private String status; // 状态
    private String estimatedArrivalTime; // 预计到达时间

    // 构造函数
    public MaterialInfo(String submitTime, String approvalTime, String name, String model,
                        String specification, int quantity, String usage,
                        String status, String estimatedArrivalTime) {
        this.submitTime = submitTime;
        this.approvalTime = approvalTime;
        this.name = name;
        this.model = model;
        this.specification = specification;
        this.quantity = quantity;
        this.usage = usage;
        this.status = status;
        this.estimatedArrivalTime = estimatedArrivalTime;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(String estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
// Getter 和 Setter 省略
}
