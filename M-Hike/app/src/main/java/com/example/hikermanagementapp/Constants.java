package com.example.hikermanagementapp;

public class Constants {
    public static final String DATABASE_NAME ="HIKE_DB";

    public static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME="HIKE_TABLE";

    public static final String H_ID = "ID";
    public static final String H_NAME = "NAME";
    public static final String H_LOCATION="LOCATION";
    public static final String H_PARKING="PARKING";
    public static final String H_LENGTH="LENGTH";
    public static final String H_LEVEL="LEVEL";
    public static final String H_DESCRIPTION="DESCRIPTION";
    public static final String H_COST="COST";
    public static final String H_WEATHER="WEATHER";

    static final String H_DATE = "DATE" ;
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
            + H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + H_NAME + " TEXT, "
            + H_LOCATION + " TEXT, "
            + H_PARKING + " TEXT, "
            + H_DATE + " TEXT, "
            + H_LENGTH + " TEXT, "
            + H_LEVEL + " TEXT, "
            + H_DESCRIPTION + " TEXT, "
            + H_COST + " TEXT, "
            + H_WEATHER + " TEXT "
            +" );";
    //Create Table Observation
    //Create table name
    public static final String OBS_TABLE_NAME = "OBSERVATION";

    //Create table attributes
    public static final String O_ID = "OBSERVATIONID";
    public static final String O_NAME = "NAME";
    public static final String O_TIME ="TIME";
    public static final String O_COMMENT ="COMMENT";
    public static final String O_HIKEID ="HIKEID";
    public static final String OBS_CREATE_TABLE = "CREATE TABLE " + OBS_TABLE_NAME + "( "
            + O_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + O_NAME + " TEXT, "
            + O_TIME + " TEXT, "
            + O_COMMENT + " TEXT, "
            + O_HIKEID + " TEXT"
            + " );";
}
