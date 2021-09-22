package com.example.componentdemo.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.componentdemo.R;


public class EditTextWithClear extends FrameLayout
{
    EditText etText;
    Button btnClear;
    public EditTextWithClear( Context context,  AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_edittext_with_clear, this);
        etText = findViewById(R.id.et);
        btnClear = findViewById(R.id.btnClear);
        //點擊清除按鈕，清除文字
        btnClear.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                etText.setText("");
            }
        });
        //取得物件xml上屬性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ComponentEditText);
        boolean bIsNecessary = array.getBoolean(R.styleable.ComponentEditText_is_necessary,false);
        //若為屬性為必填，則變色
        if(bIsNecessary)
            etText.setBackgroundResource(R.drawable.bg_common_edittext_required);
        //填入hint string
        String strHint = array.getString(R.styleable.ComponentEditText_hint);
        etText.setHint(strHint);
        array.recycle();
    }
    public void setText(String Text)
    {
        etText.setText(Text);
    }

    public String getText(String Text)
    {
        return etText.getText().toString();
    }

    public void setHint(String Text)
    {
        etText.setHint(Text);
    }

    public void setNescesssary(boolean bool)
    {
        if(bool)
            etText.setBackgroundResource(R.drawable.bg_common_edittext_required);
        else
            etText.setBackgroundResource(R.drawable.bg_common_edittext);
    }

}

