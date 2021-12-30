package com.hamza.glucoz.dao;

import com.hamza.glucoz.model.Glycemie;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaoGlycemie {
    private DatabaseReference databaseReference;
    public DaoGlycemie()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Glycemie.class.getSimpleName());
    }
     public Task<Void> add(Glycemie g)
     {
         return databaseReference.push().setValue(g);
     }
}
