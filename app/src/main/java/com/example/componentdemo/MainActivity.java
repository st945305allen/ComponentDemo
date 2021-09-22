package com.example.componentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.componentdemo.Component.AutoCompleteEditTextWithClear;
import com.example.componentdemo.Component.EditTextWithClear;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditTextWithClear etc = findViewById(R.id.etc1);
        AutoCompleteEditTextWithClear aetc = findViewById(R.id.etc2);
        String[] str = {"Xavier","Nina","GGININ","Ming","Joe"};//定義資料內容
        ArrayList<String> arr = new ArrayList<String>();
        for (int i = 0;i<200;i++)
        {
            arr.add(String.valueOf(i));
        }

        aetc.setAdapter(arr);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}