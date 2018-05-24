package com.example.islab_kc_319.loginscreen.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by islab-kc-319 on 2017/09/01.
 */

public class InputValidation {

    private Context context;

    public InputValidation(Context context){
        this.context = context;
    }

    public boolean isEditTextFilled(EditText textInputEditText, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
            textInputEditText.setError(message);
            hideKeyboardForm(textInputEditText);
            return false;
        }else{
        }
        return true;
    }

    public boolean isEditTextEmail(EditText textInputEditText, String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputEditText.setError(message);
            hideKeyboardForm(textInputEditText);
            return false;
        }else{
        }
        return true;
    }

    public boolean isEditTextMatches(EditText textInputEditText1, EditText textInputEditText2, String message){
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if(!value1.contentEquals(value2)){
            hideKeyboardForm(textInputEditText2);
            return false;
        }else{
        }
        return true;
    }

    private void hideKeyboardForm(View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
