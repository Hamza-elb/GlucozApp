package com.hamza.glucoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class diabett3 extends AppCompatActivity {

    ImageView imag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabett3);


        imag3 = (ImageView) findViewById(R.id.imgDiab3);

        imag3.setOnClickListener(view -> {
        Intent intent = new Intent(diabett3.this,LoginActivity.class);
        startActivity(intent);
    });


}}