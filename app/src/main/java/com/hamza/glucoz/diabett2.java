package com.hamza.glucoz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class diabett2 extends AppCompatActivity {

    ImageView myimag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabett2);

        myimag2 = (ImageView) findViewById(R.id.imag2);
        myimag2.setOnClickListener(view -> {
            Intent intent = new Intent(diabett2.this,diabett3.class);
            startActivity(intent);
        });

    }


}