package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends AppCompatActivity {
    TextView name, email;
    FirebaseAuth fAuth;
    ImageView editImg,ressPassImg;
    FirebaseUser user;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.id_nameP);
        email = findViewById(R.id.id_emailP);
        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        String userId= user.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference("users").child(userId);
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
                Toast.makeText(Profile.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editImg = findViewById(R.id.id_editP);
        editImg.setOnClickListener(view -> {
            Intent intent = new Intent(Profile.this,EditProfile.class);
            startActivity(intent);
        });

    }

//    private void showprofile(FirebaseUser user) {
//        String userId= fAuth.getCurrentUser().getUid();
//        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("register users");
//        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                //ReadWriteUserDetails readWriteUserDetails = snapshot.getValue()
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}