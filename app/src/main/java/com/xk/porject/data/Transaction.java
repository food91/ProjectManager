package com.xk.porject.data;

public class Transaction {

    private String date;
    private String event;
    private String amount;
    private String balance;

    public Transaction(String date, String event, String amount, String balance) {
        this.date = date;
        this.event = event;
        this.amount = amount;
        this.balance = balance;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", event='" + event + '\'' +
                ", amount='" + amount + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
