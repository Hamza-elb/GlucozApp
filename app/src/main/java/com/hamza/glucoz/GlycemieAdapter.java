package com.hamza.glucoz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.hamza.glucoz.model.Glycemie;

public class GlycemieAdapter extends FirebaseRecyclerAdapter<Glycemie,GlycemieAdapter.myViewHolder> {

    public GlycemieAdapter(@NonNull FirebaseRecyclerOptions<Glycemie> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Glycemie model) {
         holder.valG.setText(String.valueOf(model.getConcentration()) );
         holder.dateG.setText(model.getDate());
         holder.heureG.setText(model.getTime());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.glycemie_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView valG, dateG, heureG;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            valG = itemView.findViewById(R.id.historiqueG);
            dateG = itemView.findViewById(R.id.dateG);
            heureG = itemView.findViewById(R.id.heureG);
        }
    }

}
