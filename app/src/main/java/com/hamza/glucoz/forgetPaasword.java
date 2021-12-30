package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class forgetPaasword extends AppCompatActivity {

    EditText emailInput;
    ImageView reset;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_paasword);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emailInput = findViewById(R.id.id_emailreset);
        reset = findViewById(R.id.id_forgetImg);
        fauth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });






    }

    private void resetPassword() {
        String email = emailInput.getText().toString().trim();

        if(email.isEmpty()){
            emailInput.setError("Email is required !!!");
            emailInput.requestFocus();
            return;
        }



        fauth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgetPaasword.this, "check your email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }else{
                    Toast.makeText(forgetPaasword.this, "Error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //retour
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}