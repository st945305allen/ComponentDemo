package com.example.componentdemo.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.componentdemo.R;

import java.util.ArrayList;


public class AutoCompleteEditTextWithClear extends FrameLayout
{
    AutoCompleteTextView etText;
    Button btnClear;
    public AutoCompleteEditTextWithClear(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_auto_complete_edittext_with_clear, this);
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
        etText.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(etText.getText().toString().length() == 0)
                    etText.showDropDown();
            }
        });
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
    public void setAdapter(ArrayList arr)
    {
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arr);
        etText.setAdapter(adapter);
        etText.setThreshold(0);
    }

}

