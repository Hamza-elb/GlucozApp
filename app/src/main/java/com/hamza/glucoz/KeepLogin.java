package com.hamza.glucoz;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class KeepLogin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        if(user != null){

            Intent intent = new Intent(KeepLogin.this,Dashbord.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(intent);

        }
    }
}
