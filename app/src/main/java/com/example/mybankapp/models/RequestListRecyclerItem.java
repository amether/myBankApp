package com.example.mybankapp.models;


import java.util.Objects;

public class RequestListRecyclerItem {

    private String bankName;
    private Integer requestCount;
    private int percent;
    private int moneyCount;
    private String requestState;
    private int time;

    @Override
    public String toString() {
        return "RequestListRecyclerItem{" +
                "bankName='" + bankName + '\'' +
                ", requestCount=" + requestCount +
                ", percent=" + percent +
                ", moneyCount=" + moneyCount +
                ", requestState='" + requestState + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestListRecyclerItem that = (RequestListRecyclerItem) o;
        return percent == that.percent &&
                moneyCount == that.moneyCount &&
                time == that.time &&
                Objects.equals(bankName, that.bankName) &&
                Objects.equals(requestCount, that.requestCount) &&
                Objects.equals(requestState, that.requestState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, requestCount, percent, moneyCount, requestState, time);
    }

    public RequestListRecyclerItem(String bankName, Integer requestCount, int percent, int moneyCount, String requestState, int time) {
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
