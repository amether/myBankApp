package com.example.mybankapp.classes;


public class RecyclerRequestItem {

    private String bankName;
    private Integer requestCount;
    private int percent;
    private int moneyCount;
    private String requestState;
    private int time;

    public RecyclerRequestItem(String bankName, Integer requestCount, int percent, int moneyCount, String requestState, int time) {
        this.bankName = bankName;
        this.requestCount = requestCount;
        this.percent = percent;
        this.moneyCount = moneyCount;
        this.requestState = requestState;
        this.time = time;
    }

    public String getBankName() {
        return bankName;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public int getPercent() {
        return percent;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public String getRequestState() {
        return requestState;
    }

    public int getTime() {
        return time;
    }
}
