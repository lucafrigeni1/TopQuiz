package com.example.topquiz.Controleur;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.topquiz.Modele.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Preferences {

    //TODO 1) initialiser les prefs de manière static
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    public static final String PREF_KEY_LIST_SCORE = "PREF_KEY_LIST_SCORE";

    public ArrayList<User> listUser;
    public String listJson;

    mPreferences = getSharedPreferences("TopQuiz", MODE_PRIVATE);



    //TODO 2) methodes prennant en param un User et le rajoute dans la scoreList

    private void stringToList(){
        listJson = mPreferences.getString(PREF_KEY_LIST_SCORE, "");
        Log.e("JsonList", listJson + "1");
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        listUser = gson.fromJson(listJson, userListType);
        if (listUser == null){
            listUser = new ArrayList<>();
        }
        mUser.setmScore(score);
        listUser.add(mUser);
    }

    //TODO 3) methodes renvoyant la scorlist

    //Itent?


    //TODO 4 methode enregistrant le score + nom

    private void listToString(){
        Gson gson = new Gson();
        listJson = gson.toJson(listUser);
        mPreferences.edit().putString(PREF_KEY_LIST_SCORE,listJson).apply();
    }







}
