package com.example.topquiz.Controleur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.Modele.User;
import com.example.topquiz.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreList extends AppCompatActivity {

    public Button menuButton;
    public SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        RecyclerView rvScore = (RecyclerView) findViewById(R.id.rvScore);

        menuButton = findViewById(R.id.Menu);

        mPreferences = getSharedPreferences("TopQuiz",MODE_PRIVATE);
        String listJson = mPreferences.getString(MainActivity.PREF_KEY_LIST_SCORE, "");
        Log.e("JsonList", listJson + "1");
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> listUser = gson.fromJson(listJson, userListType);
        if (listUser == null){
            listUser = new ArrayList<>();
        }
        ScoreListAdapter adapter = new ScoreListAdapter(listUser);
        rvScore.setAdapter(adapter);
        rvScore.setLayoutManager(new LinearLayoutManager(this));

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

