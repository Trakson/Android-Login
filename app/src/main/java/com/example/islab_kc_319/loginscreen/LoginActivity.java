package com.example.islab_kc_319.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islab_kc_319.loginscreen.helper.InputValidation;
import com.example.islab_kc_319.loginscreen.sql.DatabaseHelper;
import com.example.islab_kc_319.loginscreen.model.User;
/**
 * Created by islab-kc-319 on 2017/08/31.
 */

public class LoginActivity extends AppCompatActivity {

    private final AppCompatActivity activity = LoginActivity.this;

    private EditText editTextUsername, editTextPassword;
    private TextView textViewSignUp;
    private Button buttonSignIn;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
        initObjects();
    }

    public void initViews(){
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);
    }

    public void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonSignIn:
                verifyFromSQLite();
                break;
            case R.id.textViewSignUp:
                Intent sign = new Intent(this, RegisterActivity.class);
                startActivity(sign);
                break;

        }
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isEditTextFilled(editTextUsername, getString(R.string.error_message_username))){
            return;
        }

        if(!inputValidation.isEditTextFilled(editTextPassword, getString(R.string.error_message_password))){
            return;
        }

        if(!databaseHelper.checkUser(editTextUsername.getText().toString().trim(), editTextUsername.getText().toString().trim())){
            Intent accountsIntent = new Intent(activity, UsersActivity.class);
            accountsIntent.putExtra("USERNAME", editTextUsername.getText().toString().trim());
            emptyEditText();
            startActivity(accountsIntent);
        }
    }

    public void emptyEditText(){
        editTextUsername.setText(null);
        editTextPassword.setText(null);
    }


}
