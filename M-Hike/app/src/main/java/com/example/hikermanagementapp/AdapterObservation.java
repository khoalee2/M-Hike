package com.example.hikermanagementapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterObservation extends RecyclerView.Adapter<AdapterObservation.ObservationViewHolder>{

    private Context context;
    private ArrayList<ModelObservation> obserList;
    private DbHelper dbHelper;

    public AdapterObservation(Context context, ArrayList<ModelObservation> obserList){
        this.context= context;
        this.obserList=obserList;
        dbHelper=new DbHelper(context);
    }

    @NonNull
    @Override
    public AdapterObservation.ObservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_obser_item,parent,false);
        AdapterObservation.ObservationViewHolder vh=new AdapterObservation.ObservationViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterObservation.ObservationViewHolder holder, int position) {
         ModelObservation modelObservation = obserList.get(position);

        String id=modelObservation.getId();
        String name=modelObservation.getName();
        String time=modelObservation.getTime();
        String comment=modelObservation.getComment();

        holder.obserName.setText(modelObservation.getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context, ObservationDetails.class);
                intent.putExtra("obserID",id);
                context.startActivity(intent);
            }
        });
        holder.obserDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.deleteObservation(id);
                ((HikeDetails)context).onResume();

            }
        });
        holder.obserEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context, AddObservation.class);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("TIME",time);
                intent.putExtra("COMMENT",comment);
                intent.putExtra("isEditObservation",true);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return obserList.size();
    }

    class ObservationViewHolder extends RecyclerView.ViewHolder{

        TextView obserName,obserDelete,obserEdit;
        RelativeLayout relativeLayout;
        public ObservationViewHolder(@NonNull View itemView){
            super(itemView);

            obserName = itemView.findViewById(R.id.obser_name);
            obserDelete =itemView.findViewById(R.id.obserDelete);
            obserEdit =itemView.findViewById(R.id.obserEdit);
            relativeLayout=itemView.findViewById(R.id.obsermainLayout);

        }
    }
}
