package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {
    EditText put;
    TextView out;
    ImageView cnv;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        put=findViewById(R.id.id_calculPut);
        out=findViewById(R.id.id_calculOut);
        cnv=findViewById(R.id.id_btnConvert);

        cnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String Put = put.getText().toString().trim();
               if(TextUtils.isEmpty(Put)){
                   Toast.makeText(Calculator.this, "Add value", Toast.LENGTH_SHORT).show();
               }else{
                   double p = Float.parseFloat(Put);
                   out.setText(""+((p * 7)/150));

               }





            }
        });
        
 //dd
        //double Put=put.getText().toString().trim();
        //double p=Double.parseDouble(Put);
        //double o = (* 7) / 1.5;

       //out.setText(Double.toString(o));

        //String Put=put.getText().toString();
        //double p = Double.parseDouble(put.getText().toString());
        //double o = (p*7)/1.5;
        //String r = Double.toString(o);
        //out.setText(r);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}