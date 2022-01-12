package com.hamza.glucoz.dao;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hamza.glucoz.model.Cetones;
import com.hamza.glucoz.model.Glycemie;

public class DaoCetones {
    private DatabaseReference databaseReference;
    public DaoCetones()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Cetones.class.getSimpleName());
    }
    public Task<Void> add(Cetones c)
    {
        return databaseReference.push().setValue(c);
    }
}
