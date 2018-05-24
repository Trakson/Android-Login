package com.example.islab_kc_319.loginscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.islab_kc_319.loginscreen.helper.InputValidation;
import com.example.islab_kc_319.loginscreen.sql.DatabaseHelper;
import com.example.islab_kc_319.loginscreen.model.User;

/**
 * Created by islab-kc-319 on 2017/09/01.
 */

public class RegisterActivity extends AppCompatActivity {

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword, editTextEmailAddress;
    private TextView textViewSignIn;
    private Button buttonSignUp;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initViews();
        initObjects();
    }

    public void initViews(){
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
    }

    public void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
        user = new User();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonSignUp:
                postDataToSQLite();
                break;
            case R.id.textViewSignIn:
                Intent sign = new Intent(this, LoginActivity.class);
                startActivity(sign);
                break;
        }
    }

    private void postDataToSQLite(){
        if(!inputValidation.isEditTextFilled(editTextUsername, getString(R.string.error_message_username))){
            return;
        }

        if(!inputValidation.isEditTextFilled(editTextEmailAddress, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation.isEditTextEmail(editTextEmailAddress, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation.isEditTextFilled(editTextPassword, getString(R.string.error_message_password))){
            return;
        }

        if(!inputValidation.isEditTextMatches(editTextPassword, editTextConfirmPassword, getString(R.string.error_password_match))){
            return;
        }

        if(!databaseHelper.checkUser(editTextEmailAddress.getText().toString().trim())){
            user.setName(editTextUsername.getText().toString().trim());
            user.setEmail(editTextEmailAddress.getText().toString().trim());
            user.setPassword(editTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
        }
    }

    public void emptyEditText(){
        editTextUsername.setText(null);
        editTextEmailAddress.setText(null);
        editTextPassword.setText(null);
        editTextConfirmPassword.setText(null);
    }

}
