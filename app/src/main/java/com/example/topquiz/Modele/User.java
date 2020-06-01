package com.example.topquiz.Modele;

public class User {
    private String mName;
    private int mScore;

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

