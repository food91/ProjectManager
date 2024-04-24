package com.xk.porject.data;

public class TableItem {
    private String month; // 月份
    private double projectFunds; // 工程款
    private double salary; // 人民工资
    private double substituteFee; // 代教费
    private double debt; // 结欠
    private String entryCollection; // 填写/合集

    // 构造函数
    public TableItem(String month, double projectFunds, double salary, double substituteFee, double debt, String entryCollection) {
        this.month = month;
        this.projectFunds = projectFunds;
        this.salary = salary;
        this.substituteFee = substituteFee;
        this.debt = debt;
        this.entryCollection = entryCollection;
    }

    // Getter 和 Setter 方法
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getProjectFunds() {
        return projectFunds;
    }

    public void setProjectFunds(double projectFunds) {
        this.projectFunds = projectFunds;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSubstituteFee() {
        return substituteFee;
    }

    public void setSubstituteFee(double substituteFee) {
        this.substituteFee = substituteFee;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public String getEntryCollection() {
        return entryCollection;
    }

    public void setEntryCollection(String entryCollection) {
        this.entryCollection = entryCollection;
    }
}

