package com.hamza.glucoz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.hamza.glucoz.model.Glycemie;

public class DataGlycemie extends AppCompatActivity {
    RecyclerView recyclerView;
    GlycemieAdapter glycemieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_glycemie);
        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Glycemie> options =
                new FirebaseRecyclerOptions.Builder<Glycemie>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Glycemie"),Glycemie.class)
                .build();

        glycemieAdapter = new GlycemieAdapter(options);
        recyclerView.setAdapter(glycemieAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        glycemieAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        glycemieAdapter.stopListening();
    }

    //retour
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(getApplicationContext(),Dashbord.class));
        finish();
        return super.onOptionsItemSelected(item);
    }
}