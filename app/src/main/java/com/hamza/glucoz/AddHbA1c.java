package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Calendar;


import com.hamza.glucoz.dao.DaoHba1c;

import com.hamza.glucoz.model.Hba1c;


public class AddHbA1c extends AppCompatActivity {

    int hour;
    int minute;
    DatePickerDialog.OnDateSetListener setListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_hb_a1c);
        //calendar
        //etDate=findViewById(R.id.et_date);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        ////il doivent  etre declaré ici sinon l'interface clignote
        final EditText etDate=findViewById(R.id.et_date);
        final EditText etTime=findViewById(R.id.et_time);
        final EditText etConcentration=findViewById(R.id.et_concetration);
        FloatingActionButton validateBtn=findViewById(R.id.floatingActionButton_hba1c);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        AddHbA1c.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day +"/"+month+"/"+ year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        //time
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniotialize time picker dialog
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        AddHbA1c.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        //Initialize hour and minute
                        hour=hourOfDay;
                        minute=minute;
                        //Initialize calendar
                        Calendar calendar1=Calendar.getInstance();
                        //set hour and minute
                        calendar1.set(0,0,0,hour,minute);
                        //set selected time on edit view
                        String time=hour +":"+minute ;
                        etTime.setText(time);

                    }

                },12,0,true
                );
                //displayed previos selected time
                timePickerDialog.updateTime(hour,minute);
                //show dialog
                timePickerDialog.show();
            }
        });

        //this lines are added for db
        //etConcentration=findViewById(R.id.et_concetration);
        validateBtn=findViewById(R.id.floatingActionButton_hba1c);

        DaoHba1c dao=new DaoHba1c();
        validateBtn.setOnClickListener(v->{
            Hba1c h=new Hba1c(Float.valueOf(etConcentration.getText().toString()),etDate.getText().toString(),etTime.getText().toString());

            dao.add(h).addOnSuccessListener(suc->{
                Toast.makeText(this,"Record is inserted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddHbA1c.this,Dashbord.class);
                startActivity(intent);

            }).addOnFailureListener(er->
            {
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

}