package com.example.componentdemo.Component;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.componentdemo.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EditTextWithScanCode extends FrameLayout
{
    EditText etText;
    Button btnScan;
    public EditTextWithScanCode(final Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.component_edittext_with_scan_code, this);
        etText = findViewById(R.id.et);
        btnScan = findViewById(R.id.btnScan);
        //點擊按鈕，則開啟相機掃碼畫面
        btnScan.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Activity activity = (Activity)context;

                IntentIntegrator scanIntegrator = new IntentIntegrator(activity);
                //scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.DATA_MATRIX,IntentIntegrator.QR_CODE);
                scanIntegrator.initiateScan();
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            if(result.getContents() == null) {
//                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }
}
