package com.example.topquiz.Controleur;

import com.example.topquiz.Modele.User;

import java.util.Comparator;

public class ScoreComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return (o1.getmScore() - o2.getmScore());
    }
}
