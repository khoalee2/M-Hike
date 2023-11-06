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

public class AdapterHike extends RecyclerView.Adapter<AdapterHike.HikeViewHolder> {

    private Context context;
    private ArrayList<ModelHike> hikeList;
    private DbHelper dbHelper;

    public AdapterHike(Context context, ArrayList<ModelHike> hikeList){
        this.context= context;
        this.hikeList=hikeList;
        dbHelper=new DbHelper(context);
    }

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_hike_item,parent,false);
        HikeViewHolder vh=new HikeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {
        ModelHike modelHike = hikeList.get(position);

        String id=modelHike.getId();
        String name=modelHike.getName();
        String location=modelHike.getLocation();
        String parking=modelHike.getParking();
        String date=modelHike.getDate();
        String length=modelHike.getLength();
        String level=modelHike.getLevel();
        String description=modelHike.getDescription();
        String cost=modelHike.getCost();
        String weather=modelHike.getWeather();

        holder.hikeName.setText(modelHike.getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context, HikeDetails.class);
                intent.putExtra("hikeID",id);
                context.startActivity(intent);
            }
        });
        holder.hikeDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.deleteHike(id);
                ((MainActivity)context).onResume();
            }
        });
        holder.hikeEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(context, AddHike.class);
                intent.putExtra("ID",id);
                intent.putExtra("NAME",name);
                intent.putExtra("LOCATION",location);
                intent.putExtra("PARKING",parking);
                intent.putExtra("DATE",date);
                intent.putExtra("LENGTH",length);
                intent.putExtra("LEVEL",level);
                intent.putExtra("DESCRIPTION",description);
                intent.putExtra("COST",cost);
                intent.putExtra("WEATHER",weather);

                intent.putExtra("isEditMode",true);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hikeList.size();
    }

    class HikeViewHolder extends RecyclerView.ViewHolder{

        ImageView hikeInfo;
        TextView hikeName,hikeDelete,hikeEdit;
        RelativeLayout relativeLayout;
        public HikeViewHolder(@NonNull View itemView){
            super(itemView);

            hikeName = itemView.findViewById(R.id.hike_name);
            hikeInfo =itemView.findViewById(R.id.hike_info);
            hikeDelete =itemView.findViewById(R.id.hikeDelete);
            hikeEdit =itemView.findViewById(R.id.hikeEdit);
            relativeLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
