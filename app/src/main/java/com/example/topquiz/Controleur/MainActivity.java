package com.example.topquiz.Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.Modele.User;
import com.example.topquiz.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private TextView mwelcome;
    private EditText mplayerName;
    private Button mplay;
    private Button bscore;
    private User mUser;
    private Preferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate()");

        mUser = new User();
        mPreferences = new Preferences(this);

        findViewById();
        goToScore();
        userAllowToPlay();
        greetUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.setScore(score);
            mPreferences.addUser(mUser, score);

            scoreListIntent();
            greetUser();
        }
    }

    private void findViewById() {
        mwelcome = findViewById(R.id.Welcome);
        mplayerName = findViewById(R.id.Name);
        mplay = findViewById(R.id.Play);
        bscore = findViewById(R.id.Score_button);
    }

    private void goToScore() {
        bscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ScoreActivityIntent = new Intent(MainActivity.this, ScoreList.class);
                startActivity(ScoreActivityIntent);
            }
        });
    }

    private void userAllowToPlay() {
        mplay.setEnabled(false);

        mplayerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mplay.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mplayerName.getText().toString();
                mUser.setName(firstName);
                mPreferences.setFirstname(firstName);
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void scoreListIntent() {
        Intent ScoreListIntent = new Intent(MainActivity.this, ScoreList.class);
        startActivity(ScoreListIntent);
    }

    private void greetUser() {
        User user = mPreferences.getUser();

        if (user.getName() != null) {
            String fulltext = "Welcome back, " + user.getName()
                    + "!\nYour last score was " + user.getmScore()
                    + ", will you do better this time?";
            mwelcome.setText(fulltext);
            mplayerName.setText(user.getName());
            mplayerName.setSelection(user.getName().length());
            mplay.setEnabled(true);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }

}
