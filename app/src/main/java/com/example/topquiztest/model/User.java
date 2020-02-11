package com.example.topquiztest.model;

public class User {
    private String mFirstNAme;

    public String getFirstNAme() {
        return mFirstNAme;
    }

    public void setFirstNAme(String firstNAme) {
        mFirstNAme = firstNAme;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstNAme='" + mFirstNAme + '\'' +
                '}';
    }
}
