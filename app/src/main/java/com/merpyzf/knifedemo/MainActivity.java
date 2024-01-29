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
        findViewById(R.id.btnHighlight).setOnClickListener(v -> knifeText.fromHtml("<mark style=\"background-color:-3609096\">12</mark>34<mark style=\"background-color:-3609096\">56</mark>7<mark style=\"background-color:-3609096\">89</mark>"));
        findViewById(R.id.btnUndo).setOnClickListener(v -> knifeText.undo());
        findViewById(R.id.btnRedo).setOnClickListener(v -> knifeText.redo());
    }
}