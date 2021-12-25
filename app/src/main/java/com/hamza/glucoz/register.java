package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class register extends AppCompatActivity {

EditText name, email, pass, verifpass;
    ImageView register;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseApp.initializeApp(this);
        name = findViewById(R.id.id_name);
        email = findViewById(R.id.id_email);
        pass = findViewById(R.id.id_pass);
        verifpass = findViewById(R.id.id_verifpass);
        register = findViewById(R.id.id_registerImg);

        fAuth = FirebaseAuth.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString().trim();
                String Verif = verifpass.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if (TextUtils.isEmpty(Name)){
                    name.setError("Name Is Required !!");
                    return;
                }

                if (TextUtils.isEmpty(Email)){
                    email.setError("Email Is Required !!");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    pass.setError("Password Is Required !!");
                    return;
                }

                if (!TextUtils.equals(Password,Verif)){
                    verifpass.setError("Is Differnt to pass !!");
                    return;
                }

                registertodb(Name,Email,Password);

            }
        });


    }

    private void registertodb(String name, String email, String password) {
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser rUser=fAuth.getCurrentUser();
                            assert rUser != null;
                            String userId=rUser.getUid();
                            databaseReference=FirebaseDatabase.getInstance().getReference("users").child(userId);
                            HashMap<String,String > hashMap = new HashMap<>();
                            hashMap.put("userId",userId);
                            hashMap.put("name",name);
                            hashMap.put("email",email);
                            //hashMap.put("password",password);
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this,"User added successfuly!!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                    }else{
                                        Toast.makeText(register.this,"Error!!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }else{
                           Toast.makeText(com.hamza.glucoz.register.this, Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
                });
    }




}