package com.example.testsamples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT game, round, darts FROM "+TABLE_NAME+" ORDER BY _id DESC LIMIT 1", null);
        cursor.moveToFirst();
        if(cursor.getCount()==1) {
            game_count = cursor.getInt(0) + 1;
            round_count = 0;
            darts_count = 0;
        }
        cursor.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_QUERY);
        onCreate(sqLiteDatabase);
    }

    private static int game_count = 0;
    private static int round_count = 0;
    private static int darts_count = 0;

    public static int getGame_count(){return game_count;}
    public static int getRound_count(){return round_count;}
    public static int getDarts_count(){return darts_count;}

    private static void record(Context context, String hit, int point){
        SQLiteDatabase sqLiteDatabase = new DartsRecordHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("game", game_count++);
        contentValues.put("round", round_count++);
        contentValues.put("darts", darts_count++); darts_count%=3;
        contentValues.put("hit", hit);
        contentValues.put("point", point);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public static int score(Context context, String hit){
        int score = 0;
        Log.d("DRH.score", "hit = /"+hit);
        Log.d("DRH.score", "substring01=/"+hit.substring(0,1));
        if (hit.equals("MISS")) {
            score=0;
        }else if (hit.equals("BULL")){
            score=50;
        }else if (hit.equals("D-BULL")){
            score = 50;
        }else if (hit.substring(0, 1).equals(" ") || hit.substring(0, 1).equals("S")){
            score=Integer.parseInt(hit.substring(1));
            Log.d("DRH.score", "Single"+score);
        }else if (hit.substring(0, 1).equals("D")){
            score=Integer.parseInt(hit.substring(1))*2;
        }else if (hit.substring(0, 1).equals("T")){
            score=Integer.parseInt(hit.substring(1))*3;
        }else{
            //BACK
        }
        record(context, hit, score);
        return score;
    }
}
