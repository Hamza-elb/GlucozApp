package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    EditText email , pass;
    ImageView signUp , signIn;
    FirebaseAuth fAuth;
    TextView forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        signUp = (ImageView) findViewById(R.id.signUp);
        email= findViewById(R.id.id_emaillog);
        pass= findViewById(R.id.id_passlog);
        fAuth=FirebaseAuth.getInstance();
        signIn=findViewById(R.id.id_signIn);
        forgetPass=findViewById(R.id.id_forget);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Password = pass.getText().toString().trim();
                if (TextUtils.isEmpty(Email)){
                    email.setError("Email Is Required !!");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    pass.setError("Password Is Required !!");
                    return;
                }
                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"logged in successfully!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Dashbord.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"Error!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,register.class);
            startActivity(intent);
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,forgetPaasword.class);
                startActivity(intent);
            }
        });

    }


}