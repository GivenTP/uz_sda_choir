package com.gtptechnologies.uzsdachoir;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //to return the single instance of Database
    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance= new DatabaseAccess(context);
        }
        return instance;
    }

    //to open the database
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    //closing the database connection
    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    //Methods to get data
    public Cursor getData(String tableName, int i){
        Cursor c=db.rawQuery("select * from "+tableName+" where ROWID = "+i,null);
        return c;
    }

    public Cursor getData(String tableName){
        Cursor c=db.rawQuery("select * from "+tableName, null);
        return c;
    }



}
