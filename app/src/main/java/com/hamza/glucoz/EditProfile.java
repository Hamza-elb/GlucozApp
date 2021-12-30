package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {
    EditText name, email;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    ImageView verif;
    FirebaseUser user = fAuth.getCurrentUser();
    String userId = user.getUid();
    DatabaseReference databaseReference;
    String Name,Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.id_namePedit);
        email = findViewById(R.id.id_emailPedit);
        verif=findViewById(R.id.id_verif);
//         btn = findViewById(R.id.btnupdate);



        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UsersData usersData = snapshot.getValue(UsersData.class);
                assert usersData != null;
                name.setText(usersData.getName());
                email.setText(usersData.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });




       verif.setOnClickListener(view -> {
           if(isNameChange() || isEmailChange()){
               Toast.makeText(this,"Data change ",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),Profile.class));

           }else{
               Toast.makeText(this,"Error!!!! ",Toast.LENGTH_SHORT).show();
           }

       });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean isEmailChange() {
        if(Email == null || !Email.equals(email.getText().toString())){
            databaseReference.child("email").setValue(email.getText().toString());

            Email=email.getText().toString();
            return true;

        }else{
            return false;
        }
    }

    private boolean isNameChange() {
        if(Name == null || !Name.equals(name.getText().toString().trim())){
            databaseReference.child("name").setValue(name.getText().toString());
            Name=name.getText().toString();
         return true;
        }else {
            return false;
        }
  }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}