package com.example.mybankapp.models;

import java.util.Objects;

public class BankListRecyclerItem {

    private String title;
    private int image;

    @Override
    public String toString() {
        return "BankListRecyclerItem{" +
                "title='" + title + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankListRecyclerItem that = (BankListRecyclerItem) o;
        return image == that.image &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, image);
    }

    public BankListRecyclerItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
