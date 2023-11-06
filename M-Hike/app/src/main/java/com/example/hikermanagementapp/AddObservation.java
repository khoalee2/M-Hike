package com.example.hikermanagementapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class AddObservation extends AppCompatActivity {

    private EditText nameObsEN,commentEN;
    private FloatingActionButton fab;

    private ActionBar actionBar;
    private Boolean isEditObservation;

    private String name, comment,hikeId,obsId;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation);

        dbHelper = new DbHelper(this);

        Intent intent = getIntent();
        hikeId=intent.getStringExtra("hikeID");

        nameObsEN=findViewById(R.id.nameObsEN);
        commentEN=findViewById(R.id.commentEN);

        String time= ""+System.currentTimeMillis();

        actionBar = getSupportActionBar();

        actionBar.setTitle("Add observation");

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               saveData();
            }
        });
        isEditObservation = intent.getBooleanExtra("isEditObservation", false);
        if(isEditObservation)
        {
            actionBar.setTitle("Update Observation");

            obsId = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            comment = intent.getStringExtra("COMMENT");

            nameObsEN.setText(name);
            commentEN.setText(comment);
        }
        else
        {
            actionBar.setTitle("Create Observation");
        }
    }
    private void saveData(){
        name=nameObsEN.getText().toString();
        comment=commentEN.getText().toString();
        String currentTime = ""+System.currentTimeMillis();
        if (!name.isEmpty()){
            if(isEditObservation){
                dbHelper.updateObservation(
                        "" + obsId,
                        "" + name,
                        "" + currentTime,
                        "" + comment);

                Toast.makeText(getApplicationContext(),"Update successfully ", Toast.LENGTH_SHORT).show();
            } else {
                long id = dbHelper.insertObservation(
                        "" + name,
                        "" + currentTime,
                        "" + comment,
                        "" + hikeId);
                Toast.makeText(getApplicationContext(),"Add Observation" + id, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Lack of information", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}