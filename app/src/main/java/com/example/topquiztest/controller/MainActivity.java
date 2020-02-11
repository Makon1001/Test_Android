package com.example.topquiztest.controller;

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

import com.example.topquiztest.R;
import com.example.topquiztest.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreeningText;
    private EditText mInputName;
    private Button btnPlay;
    private User mUser;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    public static final int USER_ACTIVITY_REQUEST_CODE = 84;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            //Fetch the score from INtent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreeningText = (TextView) findViewById(R.id.main_activity_text);
        mInputName = (EditText) findViewById(R.id.main_activity_nameInput);
        btnPlay = (Button) findViewById(R.id.main_activity_btnPlay);

        mUser = new User();

        if(mPreferences != null) {
            //Already connected

            //usrname recovery
            mUser.setFirstNAme(mPreferences.getString(PREF_KEY_FIRSTNAME, "FirstName"));

            Intent user_still_connected = new Intent(MainActivity.this, UserActivity.class );
            Bundle b = new Bundle();
            b.putString(PREF_KEY_FIRSTNAME, mPreferences.getString(PREF_KEY_FIRSTNAME, "test"));
            b.putInt(PREF_KEY_SCORE, mPreferences.getInt(PREF_KEY_SCORE, 0));
            user_still_connected.putExtras(b);
            startActivity(user_still_connected);

        } else {
            btnPlay.setEnabled(false);

            mInputName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    btnPlay.setEnabled(s.toString().length() != 0);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }





        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setFirstNAme(mInputName.getText().toString());

                mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstNAme()).apply();

                Intent GameActivity_intent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(GameActivity_intent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}
