package com.aristiger.developer.guessit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onCickHollywood(View view) {
        Intent intent=new Intent(StartActivity.this,MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
    }

    public void onClickBollywood(View view) {

        Intent intent=new Intent(StartActivity.this,MainActivity.class);
        intent.putExtra("key",0);
        startActivity(intent);

    }
}
