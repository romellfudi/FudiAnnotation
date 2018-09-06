package com.romellfudi.fudi_annotation.sample.Views.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;

import com.romellfudi.fudi_annotation.sample.R;
import com.romellfudi.fudi_annotation.sample.Views.Interface.OnStepperChangeValueListener;

import java.text.DecimalFormat;

/**
 * @author Romell Dominguez
 * @version 1.0.a 01/09/2015
 * @since 1.0
 */
public class StepperView extends LinearLayout {
    int currentValue, stepValue, maxValue;
    CustomEditText edtValue;
    OnStepperChangeValueListener onStepperChangeValueListener;
    public StepperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(context.getResources().getColor(R.color.colorBorderStepper));
        this.setFocusableInTouchMode(true);
        this.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        this.setFocusable(true);
        this.setPadding(1, 1, 1, 1);
        this.setOrientation(HORIZONTAL);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StepperView);
        currentValue = typedArray.getInteger(R.styleable.StepperView_startValue, 0);
        stepValue = typedArray.getInteger(R.styleable.StepperView_stepValue, 100);
        maxValue = typedArray.getInteger(R.styleable.StepperView_maxValue, 10000000);
        typedArray.recycle();
        edtValue = new CustomEditText(getContext());
        Button minusBtn = new Button(this.getContext());
        LayoutParams paramsMinusBtn = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsMinusBtn.weight = 1;
        minusBtn.setLayoutParams(paramsMinusBtn);
        minusBtn.setText("-");
        minusBtn.setBackgroundColor(getResources().getColor(R.color.colorButtonsStepper));
        minusBtn.setTextColor(getResources().getColor(R.color.colorRadioButton));
        minusBtn.setTextSize(25);
        minusBtn.setPadding(0, 0, 0, 0);
        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue -= stepValue;
                if (currentValue < 0) {
                    currentValue = 0;
                }
                updateTextView(true);
            }
        });
        this.addView(minusBtn);
        LayoutParams paramsEdtValue = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsEdtValue.weight = 3;
        paramsEdtValue.leftMargin = 1;
        paramsEdtValue.rightMargin = 1;
        edtValue.setLayoutParams(paramsEdtValue);
        edtValue.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        edtValue.setTypeface(Typeface.DEFAULT_BOLD);
        edtValue.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_ACTION_DONE);
        edtValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        edtValue.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        edtValue.setGravity(Gravity.CENTER_VERTICAL);
        edtValue.setSelectAllOnFocus(true);
        edtValue.setPadding(0, 0, 0, 0);
        edtValue.setOnFocusChangeListener(new OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    edtValue.setText(Integer.toString(currentValue));
                    edtValue.selectAll();
                }else{
                    if(edtValue.getText().length() > 0){
                        if (currentValue > maxValue) {
                            currentValue = maxValue;
                        }
                        currentValue = Integer.parseInt(edtValue.getText().toString());
                    } else {
                        currentValue = 0;
                    }
                    updateTextView(true);
                }
            }
        });
        this.addView(edtValue);
        Button plusBtn = new Button(this.getContext());
        LayoutParams paramsPlusBtn = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsPlusBtn.weight = 1;
        plusBtn.setLayoutParams(paramsPlusBtn);
        plusBtn.setText("+");
        plusBtn.setTextSize(25);
        plusBtn.setPadding(0, 0, 0, 0);
        plusBtn.setBackgroundColor(getResources().getColor(R.color.colorButtonsStepper));
        plusBtn.setTextColor(getResources().getColor(R.color.colorRadioButton));
        plusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue += stepValue;
                if(currentValue > maxValue){
                    currentValue = maxValue;
                }
                updateTextView(true);
            }
        });
        this.addView(plusBtn);
        updateTextView(false);
    }
    public void setOnStepperChangeValueListener(OnStepperChangeValueListener listener){
        onStepperChangeValueListener = listener;
    }
    private void updateTextView(boolean notifyListener){
        DecimalFormat formatter = new DecimalFormat("##,###,###");
        edtValue.setText(formatter.format(currentValue));
        if(onStepperChangeValueListener != null && notifyListener){
            onStepperChangeValueListener.onChangeValue(this);
        }
    }
    public int getCurrentValue(){
        return currentValue;
    }
    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
        updateTextView(false);
    }
}