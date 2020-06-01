package com.example.topquiz.Controleur;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.topquiz.Modele.User;
import com.example.topquiz.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreList extends AppCompatActivity {

    public Button menuButton;
    public Button playerSort;
    public Button scoreSort;
    public SharedPreferences mPreferences;
    public ArrayList<User> listUser;
    RecyclerView rvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        findViewById();
        goToMenu();
        sortByName();
        sortByScore();

        mPreferences = getSharedPreferences("TopQuiz", MODE_PRIVATE);
        String listJson = mPreferences.getString(MainActivity.PREF_KEY_LIST_SCORE, "");
        Log.e("JsonList", listJson + "1");
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
        listUser = gson.fromJson(listJson, userListType);
        if (listUser == null) {
            listUser = new ArrayList<>();
        }


        rwUpdate();
        rvScore.setLayoutManager(new LinearLayoutManager(this));
    }



    private void findViewById(){
        rvScore = findViewById(R.id.rvScore);
        menuButton = findViewById(R.id.Menu);
        playerSort = findViewById(R.id.Player_sorted);
        scoreSort = findViewById(R.id.Score_sorted);
    }

    private void rwUpdate(){
        ScoreListAdapter adapter = new ScoreListAdapter(listUser);
        rvScore.setAdapter(adapter);
    }

    private void goToMenu(){
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sortByName(){
        playerSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUser, new NameComparator());
                rwUpdate();
            }
        });
    }

    private void sortByScore(){
        scoreSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(listUser, new ScoreComparator());
                rwUpdate();
            }
        });
    }

}

