package com.example.topquiz.Controleur;

import com.example.topquiz.Modele.User;

import java.util.Comparator;

public class NameComparator implements Comparator<User> {

    @Override
    public int compare(User e1, User e2) {
        return e1.getName().compareTo(e2.getName());
    }
}
