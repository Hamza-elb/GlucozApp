package com.hamza.glucoz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class reminder extends AppCompatActivity implements View.OnClickListener {
    private int notification = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

//        Setbtn = findViewById(R.id.id_btnsetreminder);
//        CancelBtn= findViewById(R.id.id_btncancelreminder);

        findViewById(R.id.id_btnsetreminder).setOnClickListener(this);
        findViewById(R.id.id_btncancelreminder).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        EditText text = findViewById(R.id.id_textreminder);
        TimePicker time = findViewById(R.id.id_timereminder);

        //Set notication
        Intent intent = new Intent(reminder.this,AlarmReceiver.class);
        intent.putExtra("notification",notification);
        intent.putExtra("message",text.getText().toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(reminder.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        switch (view.getId()){
            case R.id.id_btnsetreminder:
                int hour = time.getCurrentHour();
                int minute = time.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,hour);
                startTime.set(Calendar.MINUTE,minute);
                startTime.set(Calendar.SECOND,0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmStartTime,1000*60,pendingIntent);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Dashbord.class));
                break;

            case R.id.id_btncancelreminder:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}