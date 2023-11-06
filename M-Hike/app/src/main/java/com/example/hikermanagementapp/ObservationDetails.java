package com.example.hikermanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class ObservationDetails extends AppCompatActivity {

    private TextView nameObserTV, timeObserTV,commentObserTV;

    private String id;
    private RecyclerView obserRv;
    private AdapterObservation adapterObservation;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_details);
        dbHelper = new DbHelper(this);


        Intent intent =getIntent();
        id=intent.getStringExtra("obserID");

        nameObserTV=findViewById(R.id.nameObserTV);
        timeObserTV=findViewById(R.id.timeObserTV);
        commentObserTV=findViewById(R.id.commentObserTV);
        loadDataById();
    }

    private void loadDataById() {
        String selectQuery = "SELECT * FROM "+Constants.OBS_TABLE_NAME+" WHERE "+ Constants.O_ID+" =\"" + id + "\"";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                String name=""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_NAME));
                String time=""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_TIME));
                String comment=""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_COMMENT));

                Calendar calendar=Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(Long.parseLong(time));

                android.text.format.DateFormat df = new android.text.format.DateFormat();

                timeObserTV.setText(df.format("dd/MM/yyyy hh:mm:ss", calendar).toString());
                nameObserTV.setText(name);
                commentObserTV.setText(comment);
            }while (cursor.moveToNext());
        }
        db.close();
    }
}