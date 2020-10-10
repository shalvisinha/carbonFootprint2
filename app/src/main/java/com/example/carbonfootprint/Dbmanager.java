package com.example.carbonfootprint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbmanager extends SQLiteOpenHelper {

    public static final String dbname ="log_info.db";
    public Dbmanager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String qry1 = "CREATE TABLE info(name text not null, phone text primary key)";
    db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists info");
    onCreate(db);
    }

    public Boolean insert(String p , String n){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",p);
        cv.put("phone",n);

        long res = db.insert("info",null,cv);
        if(res==-1)
        {
            return false;
        }
        else
            return true;

    }
    public Boolean check(String phone){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from info where phone=?",new String[]{phone});
        if(cursor.getCount()>0){
            return false;

        }
        else{
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from info",null);
        return data;
    }
    public Boolean change(String na,String ph) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", na);
        long res = db.update("info", cv, "phone=?", new String[]{ph});
        if(res==-1)
            return false;
        else
            return true;

    }
    public Boolean change_ph(String nu,String ph) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("phone", nu);
        long res = db.update("info", cv, "phone=?", new String[]{ph});
        if(res==-1)
            return false;
        else
            return true;

    }
}
