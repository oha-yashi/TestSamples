package com.example.testsamples;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DartsRecordHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB.db";
    private static final int DATABASE_VERSION = 1;

    public DartsRecordHelper(@Nullable Context context) { super(context, DB_NAME, null, DATABASE_VERSION); }

    private final static String TABLE_NAME = "DartsTable";
    private final static String[] TITLES = {
            "_id integer primary key autoincrement",
            "game", "round", "darts", "hit", "point"
    };
    private final static String CREATE_QUERY = "CREATE TABLE "+ TABLE_NAME +" ("+String.join(",", TITLES)+")";
    private final static String DELETE_QUERY = "DROP TABLE "+TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_QUERY);
    }

    private static int game_count = 0;
    private static int round_count = 0;
    private static int darts_count = 0;

    private void record(Context context, String hit, int point){
        SQLiteDatabase sqLiteDatabase = new DartsRecordHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("game", game_count++);
        contentValues.put("round", round_count++);
        contentValues.put("darts", (darts_count++)%3);
    }

    public static void score(Context context, String hit){
        switch (hit){
            case "MISS":
        }
    }
}
