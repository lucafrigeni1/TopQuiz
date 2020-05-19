package com.example.topquiz.Modele;

import java.util.ArrayList;

public class User {
    private String mName;
    private int mScore;

    public User(){

    }

    public User(String name, int score){
        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "mName='" + mName + '\'' +
                '/';
    }
}

