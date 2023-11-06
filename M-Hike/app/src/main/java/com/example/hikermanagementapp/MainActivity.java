package com.example.hikermanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView hikeRv;

    private DbHelper dbHelper;

    private AdapterHike adapterHike;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar= getSupportActionBar();

        dbHelper=new DbHelper(this);

        fab=findViewById(R.id.fab);
        hikeRv=findViewById(R.id.hikeRv);

        hikeRv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        hikeRv.setLayoutManager(mLayoutManager);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddHike.class);
                intent.putExtra("isEditMode",false);
                startActivity(intent);
            }
        });
        loadData();
    }

    private void loadData() {
        adapterHike = new AdapterHike(this, dbHelper.getAllData());
        hikeRv.setAdapter(adapterHike);

    }

    @Override
    protected void onResume(){
        super.onResume();
        loadData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_top_menu,menu);

        MenuItem item = menu.findItem(R.id.searchHike);
        SearchView searchView= (SearchView) item.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchHike(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchHike(s);
                return true;
            }
        });
        return true;
    }

    private void searchHike(String s) {
        adapterHike = new AdapterHike(this,dbHelper.getSearchHike(s));
        hikeRv.setAdapter(adapterHike);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.deleteAll) {
            dbHelper.deleteAllHike();
            onResume();
        }
        return true;
    }
}