package com.example.componentdemo.Component;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.componentdemo.R;

import java.util.Calendar;


public class EditTextWithTimer extends FrameLayout
{
    TextView tvText;
    Button btnClear;
    Activity activity;

    public EditTextWithTimer(final Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_edittext_with_timer, this);
        tvText = findViewById(R.id.et);
        btnClear = findViewById(R.id.btnClear);

        btnClear.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tvText.setText("");
            }
        });
        tvText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

        //取得物件xml上屬性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ComponentEditText);
        boolean bIsNecessary = array.getBoolean(R.styleable.ComponentEditText_is_necessary,false);
        //若為屬性為必填，則變色
        if(bIsNecessary)
            tvText.setBackgroundResource(R.drawable.bg_common_edittext_required);

        //填入hint string
        String strHint = array.getString(R.styleable.ComponentEditText_hint);
        tvText.setHint(strHint);

        array.recycle();
    }
    public void setText(String Text)
    {
        tvText.setText(Text);
    }

    public String getText(String Text)
    {
        return tvText.getText().toString();
    }

    public void setHint(String Text)
    {
        tvText.setHint(Text);
    }

    private void setDate()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateTime = String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(day);
                tvText.setText(dateTime);
            }

        }, year, month, day).show();
    }
}

