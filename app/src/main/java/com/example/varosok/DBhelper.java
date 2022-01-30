package com.example.varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "varosok.db";
    public static final int DB_VERSION = 1;

    public static final String VAROSOK_TABLA = "varosok";

    public static final String COL_ID = "id";
    public static final String COL_NEV = "nev";
    public static final String COL_ORSZAG = "orszag";
    public static final String COL_LAKOSSAG = "lakossag";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + VAROSOK_TABLA + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NEV + " VARCHAR(255) NOT NULL UNIQUE, "
                + COL_ORSZAG + " VARCHAR(255) NOT NULL, "
                + COL_LAKOSSAG + " INTEGER NOT NULL" +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + VAROSOK_TABLA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public boolean adatRogzites(String nev, String orszag, String lakossag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_ORSZAG, orszag);
        values.put(COL_LAKOSSAG, lakossag);
        return db.insert(VAROSOK_TABLA, null, values) != -1;
    }

    public Cursor adatLekerdezes(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(VAROSOK_TABLA, new String[]{COL_ID, COL_NEV, COL_ORSZAG, COL_LAKOSSAG},
                null, null, null, null, null);
    }
}
