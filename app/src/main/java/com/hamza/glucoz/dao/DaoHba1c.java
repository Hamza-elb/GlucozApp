package com.hamza.glucoz.dao;

import com.hamza.glucoz.model.Glycemie;
import com.hamza.glucoz.model.Hba1c;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DaoHba1c {
    private DatabaseReference databaseReference;
    public DaoHba1c()
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Hba1c.class.getSimpleName());
    }
    public Task<Void> add(Hba1c h)
    {
        return databaseReference.push().setValue(h);
    }
}
