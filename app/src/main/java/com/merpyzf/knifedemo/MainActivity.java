package com.merpyzf.knifedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import io.github.mthli.knife.KnifeText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KnifeText knifeText = findViewById(R.id.knifeText);
        findViewById(R.id.btnHighlight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knifeText.highlight(Color.RED, !knifeText.contains(KnifeText.FORMAT_HIGHLIGHT));
            }
        });

    }
}