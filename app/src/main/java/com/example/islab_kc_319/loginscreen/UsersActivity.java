package com.example.islab_kc_319.loginscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by islab-kc-319 on 2017/09/01.
 */

public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);

        textViewName = (TextView) findViewById(R.id.textViewName);
        String nameFrontIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFrontIntent);
    }
}
