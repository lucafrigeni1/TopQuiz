package com.example.topquiz.Controleur;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.topquiz.Modele.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Preferences {

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    public static final String PREF_KEY_LIST_SCORE = "PREF_KEY_LIST_SCORE";
    //TODO 1) initialiser les prefs de manière static
    private static final int MODE_PRIVATE = 0;
    private SharedPreferences mPreferences;


    public Preferences(Context context) {
        mPreferences = context.getSharedPreferences("TopQuiz", MODE_PRIVATE);
    }

    //TODO 2) methodes prennant en param un User et le rajoute dans la scoreList

    public void addUser(User user, int score) {
        String listJson = mPreferences.getString(PREF_KEY_LIST_SCORE, "");
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> listUser = gson.fromJson(listJson, userListType);
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        user.setmScore(score);
        listUser.add(user);

        listJson = gson.toJson(listUser);
        mPreferences.edit().putString(PREF_KEY_LIST_SCORE, listJson).apply();
    }

    //TODO 3) methodes renvoyant la scorlist


    public ArrayList<User> getScoreList() {
        String listJson = mPreferences.getString(PREF_KEY_LIST_SCORE, "");
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> listUser = gson.fromJson(listJson, userListType);
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        return listUser;
    }


    //TODO 4 methode enregistrant le score + nom

    public void setFirstname(String firstname) {
        mPreferences.edit().putString(PREF_KEY_FIRSTNAME, firstname).apply();
    }

    public void setScore(int score) {
        mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
    }

    public User getUser() {
        User result = new User();
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
        int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
        result.setName(firstname);
        result.setmScore(score);
        return result;
    }
}
