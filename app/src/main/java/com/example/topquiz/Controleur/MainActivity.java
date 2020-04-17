package com.example.topquiz.Controleur;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.topquiz.Modele.User;
import com.example.topquiz.R;

public class MainActivity extends AppCompatActivity {

    private TextView mwelcome;
    private EditText mplayerName;
    private Button mplay;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User();
        mPreferences = getPreferences(MODE_PRIVATE);
        mwelcome = findViewById(R.id.Welcome);
        mplayerName = findViewById(R.id.Name);
        mplay = findViewById(R.id.Play);

        mplay.setEnabled(false);

        greetUser();

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
                mUser.setFirstName(firstName);
                mPreferences.edit().putString("Firstname", mUser.getFirstName()).apply();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
                int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
                mPreferences.edit().putInt("score", score).apply();

                greetUser();
            }
        }

        private void greetUser(){
            String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);

            if(null != firstname){
                int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

                String fulltext = "Welcome back, " + firstname
                        + "!\nYour last score was " + score
                        + ", will you do better this time?";
                mwelcome.setText(fulltext);
                mplayerName.setText(firstname);
                mplayerName.setSelection(firstname.length());
                mplay.setEnabled(true);
            }
        }


}
