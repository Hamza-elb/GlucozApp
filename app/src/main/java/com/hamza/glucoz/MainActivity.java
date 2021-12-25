package com.hamza.glucoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView myImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImg = (ImageView) findViewById(R.id.imageDiabette);
        myImg.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,diabett2.class);
            startActivity(intent);
        });
    }



}