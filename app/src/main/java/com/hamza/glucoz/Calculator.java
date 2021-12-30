package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    EditText put;
    TextView out;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        put=findViewById(R.id.id_calculPut);
        out=findViewById(R.id.id_calculOut);

        //String Put=put.getText().toString().trim();
        double p= Double.valueOf(put.getText().toString());
        double o ;
        o = (p* 7) / 1.5;

       out.setText(Double.toString(o));


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}