package com.ctis487.group8.project;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class GameReviewDB {
    public static ArrayList<Game> games;
    public static String TABLE_NAME="gamereviews";
    public static String FIELD_ID = "_id";
    public static String FIELD_GAME_ID = "game_id";
    public static String FIELD_HOURS_SPENT = "hours_spent";
    public static String FIELD_REVIEW = "review";
    public static String FIELD_RATING = "rating";

    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+" ( "+FIELD_ID+" integer, "+FIELD_GAME_ID+" number, "+FIELD_HOURS_SPENT+" number, "+FIELD_REVIEW+" text, "+ FIELD_RATING +" number, PRIMARY KEY("+FIELD_ID+" AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<GameReview> getAllGameReviews(DatabaseHelper dbHelper){
        GameReview anItem;
        ArrayList<GameReview> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            int gameId = cursor.getInt(1);
            int hoursSpent = cursor.getInt(2);
            String review = cursor.getString(3);
            int rating= cursor.getInt(4);
            anItem = new GameReview(id, gameId, hoursSpent, rating, review);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insert(DatabaseHelper dbHelper, int gameId, int hoursSpent, int rating, String review) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_GAME_ID, gameId);
        contentValues.put(FIELD_HOURS_SPENT, hoursSpent);
        contentValues.put(FIELD_RATING, rating);
        contentValues.put(FIELD_REVIEW, review);

        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, int id, int gameId, int hoursSpent, int rating, String review) {
        //ContentValues  allows to define key value pairs.
        //The key represents the table column identifier and the value represents the content for the table record in this column.
        //ContentVales can be used for insert and update operations over table
        Log.d("Hours Spent" , "" + hoursSpent);
        Log.d("Rating" , "" + rating);
        Log.d("Review" , "" + review);

        ContentValues contentValues = new ContentValues( );
        contentValues.put(FIELD_HOURS_SPENT, hoursSpent);
        contentValues.put(FIELD_RATING, rating);
        contentValues.put(FIELD_REVIEW, review);

        String where = FIELD_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }

    public static boolean delete(  DatabaseHelper dbHelper, String id){
        Log.d("DATABASE OPERATIONS", "DELETE DONE");
        String where = FIELD_ID + " = "+id;
        boolean res =  dbHelper.delete(TABLE_NAME, where);
        return  res;
    }
}
