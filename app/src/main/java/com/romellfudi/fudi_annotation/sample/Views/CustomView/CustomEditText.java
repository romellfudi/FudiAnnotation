package com.romellfudi.fudi_annotation.sample.Views.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class CustomEditText extends EditText{
    public CustomEditText(Context context){
        super(context);
        this.configure();
    }
    public CustomEditText(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.configure();
    }
    public void configure(){
        this.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == 6){
                    CustomEditText.this.clearFocus();
                    InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(CustomEditText.this.getWindowToken(), 0);
                }
                return false;
            }
        });
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
            this.clearFocus();
        }
        return super.onKeyPreIme(keyCode, event);
    }
}