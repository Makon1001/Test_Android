package com.example.topquiztest.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiztest.R;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mbtn_start;
    private Button mbtn_change_user;
    private TextView mWelcome_TextView;
    private String FirstName;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_still_connected);


        Bundle b = getIntent().getExtras();

        mbtn_start = (Button) findViewById(R.id.btn_user_start);
        mbtn_change_user = (Button) findViewById(R.id.btn_user_change_name);
        mWelcome_TextView = (TextView) findViewById(R.id.activity_connected_textView);

        mbtn_start.setTag(1);
        mbtn_change_user.setTag(2);

        mbtn_start.setOnClickListener(this);
        mbtn_change_user.setOnClickListener(this);


        if (b != null) {
            FirstName = b.getString("PREF_KEY_FIRSTNAME");
            mWelcome_TextView.setText("Bon retour parmis nous " + FirstName );
        } else {

            mWelcome_TextView.setText("Bon retour parmis nous bel inconnu");
        }



    }

    private void changeUser() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Do you realy want to change User")
                    .setMessage("Are you sure ?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent MainActivity_intent = new Intent (UserActivity.this, MainActivity.class);
                            startActivity(MainActivity_intent);
                            finish();
                        }
                    })
                    .create()
                    .show();
    }

    @Override
    public void onClick(View v) {
        int buttonIndex = (int) v.getTag();

        if (buttonIndex == 1) {
            //Start the game
            Intent GameActivity_intent = new Intent(UserActivity.this, GameActivity.class);
            startActivityForResult(GameActivity_intent, GAME_ACTIVITY_REQUEST_CODE);
        }

        if (buttonIndex == 2) {
            //Change user name back to main activity
            changeUser();
        }

    }
}
