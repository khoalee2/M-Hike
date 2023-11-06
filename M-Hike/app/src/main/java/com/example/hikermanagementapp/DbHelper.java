package com.example.hikermanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);
        db.execSQL(Constants.OBS_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +Constants.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +Constants.OBS_CREATE_TABLE);

        onCreate(db);
    }

    public long insertHike(String name,String location,String parking,String date,String length,String level,String description,String cost,String weather){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.H_NAME,name);
        contentValues.put(Constants.H_LOCATION,location);
        contentValues.put(Constants.H_PARKING,parking);
        contentValues.put(Constants.H_DATE,date);
        contentValues.put(Constants.H_LENGTH,length);
        contentValues.put(Constants.H_LEVEL,level);
        contentValues.put(Constants.H_DESCRIPTION,description);
        contentValues.put(Constants.H_COST,cost);
        contentValues.put(Constants.H_WEATHER,weather);

        long id=db.insert(Constants.TABLE_NAME,null,contentValues);

        db.close();

        return id;
    }

    public long insertObservation(String name,String time,String comment,String hikeID){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.O_NAME,name);
        contentValues.put(Constants.O_TIME,time);
        contentValues.put(Constants.O_COMMENT,comment);
        contentValues.put(Constants.O_HIKEID,hikeID);
        long id=db.insert(Constants.OBS_TABLE_NAME,null,contentValues);

        db.close();

        return id;
    }

    public void updateHike(String id,String name,String location,String parking,String date,String length,String level,String description,String cost,String weather){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.H_NAME,name);
        contentValues.put(Constants.H_LOCATION,location);
        contentValues.put(Constants.H_PARKING,parking);
        contentValues.put(Constants.H_DATE,date);
        contentValues.put(Constants.H_LENGTH,length);
        contentValues.put(Constants.H_LEVEL,level);
        contentValues.put(Constants.H_DESCRIPTION,description);
        contentValues.put(Constants.H_COST,cost);
        contentValues.put(Constants.H_WEATHER,weather);

        db.update(Constants.TABLE_NAME,contentValues,Constants.H_ID+" =? ",new String[]{id});

        db.close();
    }

    public void deleteHike(String id){
        SQLiteDatabase db=getWritableDatabase();

        db.delete(Constants.TABLE_NAME,"ID =? ",new String[]{id});
        db.delete(Constants.OBS_TABLE_NAME,"HIKEID =? ",new String[]{id});
        db.close();
    }
    public void deleteAllHike(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
        db.execSQL("DELETE FROM "+Constants.OBS_TABLE_NAME);
        db.close();
    }

    public ArrayList<ModelHike> getAllData(){
        ArrayList<ModelHike> arrayList=new ArrayList<>();

        String selectQuery = "SELECT * FROM "+Constants.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ModelHike modelHike = new ModelHike(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.H_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LOCATION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_PARKING)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_DATE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LENGTH)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LEVEL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_DESCRIPTION)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_COST)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_WEATHER))
                );
                arrayList.add(modelHike);
            }while (cursor.moveToNext());
        }
        return arrayList;
     }

     public ArrayList<ModelHike> getSearchHike(String s){
        ArrayList<ModelHike> hikeList=new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String queryToSearch = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.H_NAME + " LIKE '%" + s + "%'";
        Cursor cursor = db.rawQuery(queryToSearch, null);
         if(cursor.moveToFirst()){
             do{
                 ModelHike modelHike = new ModelHike(
                         ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.H_ID)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_NAME)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LOCATION)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_PARKING)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_DATE)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LENGTH)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LEVEL)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_DESCRIPTION)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_COST)),
                         ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_WEATHER))
                 );
                 hikeList.add(modelHike);
             }while (cursor.moveToNext());
         }
         return hikeList;
     }

    public ArrayList<ModelObservation> getAllObservationData(String id){
        ArrayList<ModelObservation> arrayList=new ArrayList<>();

        String selectQuery = "SELECT * FROM "+Constants.OBS_TABLE_NAME + " WHERE " +Constants.O_HIKEID + "=\"" +id + "\""  ;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ModelObservation ModelObservation = new ModelObservation(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.O_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_COMMENT)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.O_HIKEID))
                );
                arrayList.add(ModelObservation);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
    public void deleteObservation(String id){
        SQLiteDatabase db=getWritableDatabase();

        db.delete(Constants.OBS_TABLE_NAME,"OBSERVATIONID=?",new String[]{id});
        db.close();
    }
    public void updateObservation(String Id, String Name, String Date, String Comment) {

        //Call database
        SQLiteDatabase db = this.getWritableDatabase();

        //Create object to save data
        ContentValues content = new ContentValues();

        //Add content to table's attributes
        content.put(Constants.O_NAME, Name);
        content.put(Constants.O_TIME, Date);
        content.put(Constants.O_COMMENT, Comment);

        //Push data to database with id
        db.update(Constants.OBS_TABLE_NAME,content,Constants.O_ID + " =? ", new String[]{Id});
        db.close();
    }
}
