package com.example.modelpaper2019a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

//import static com.example.modelpaper2019a.DatabaseMaster.Users.TABLE_NAME;


public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ModelPaperA";
    public static final String TABLE_NAME = "users";
    public static final String TABLE_NAME2 = "movies";
    public static final String TABLE_NAME3 = "comments";

    public DBHandler(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
                DatabaseMaster.Users.COLS_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseMaster.Users.COLS_2 + " TEXT," +
                DatabaseMaster.Users.COLS_3 + " TEXT," +
                DatabaseMaster.Users.COLS_4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

        String SQL_CREATE_ENTRIES2 = "CREATE TABLE " + TABLE_NAME2 + " (" +
                DatabaseMaster.Movie.COLS_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseMaster.Movie.COLS_2 + " TEXT," +
                DatabaseMaster.Movie.COLS_3 + " TEXT)";
                //DatabaseMaster.Movie.COLS_4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES2);

        String SQL_CREATE_ENTRIES3 = "CREATE TABLE " + TABLE_NAME3 + " (" +
                DatabaseMaster.Comments.COLS_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseMaster.Comments.COLS_2 + " TEXT," +
                DatabaseMaster.Comments.COLS_3 + " TEXT," +
                DatabaseMaster.Comments.COLS_4 + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long RegisterUser(String UserName , String Password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Users.COLS_2,UserName);
        values.put(DatabaseMaster.Users.COLS_3,Password);
        values.put(DatabaseMaster.Users.COLS_4,UserName);

        long newRowId = db.insert(TABLE_NAME,null,values);
        return newRowId;
    }

    public Boolean Login(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where UserName = ? and Password = ?", new String[] {username,password});
        if(cursor.getCount() > 0 ){
            return true;
        }
        else
            return false;
    }

    public long addMovie(String MovieName, String MovieYear){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Movie.COLS_2,MovieName);
        values.put(DatabaseMaster.Movie.COLS_3,MovieYear);

        long newRowId = db.insert(TABLE_NAME2,null,values);
        return newRowId;
    }

    /*public Cursor getMovie(String MovieName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {DatabaseMaster.Movie.COLS_2, DatabaseMaster.Movie.COLS_3 };
        String selection = DatabaseMaster.Movie.COLS_2 + " LIKE?";
        String[] selection_args = {MovieName};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME2,projections,selection,selection_args,null,null,null);
        return cursor;
    }*/

    public long insertComment(String MovieName,String MovieRating,String MovieComment){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseMaster.Comments.COLS_2,MovieName);
        values.put(DatabaseMaster.Comments.COLS_3,MovieRating);
        values.put(DatabaseMaster.Comments.COLS_4,MovieComment);

        long newRowId = db.insert(TABLE_NAME3,null,values);
        return newRowId;
    }

    /*public Cursor getComment(String CommentName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {DatabaseMaster.Comments.COLS_3, DatabaseMaster.Comments.COLS_4,DatabaseMaster.Comments.COLS_5};
        String selection = DatabaseMaster.Comments.COLS_2 + " LIKE?";
        String[] selection_args = {CommentName};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME3,projections,selection,selection_args,null,null,null);
        return cursor;
    }*/

    public Cursor ViewMovie(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME2,null);
        return cursor;
    }

    public Cursor ViewComment(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TABLE_NAME3,null);
        return cursor;
    }

    public Cursor getComment(String MovieName, SQLiteDatabase sqLiteDatabase){
        String[] projections = {DatabaseMaster.Comments.COLS_2, DatabaseMaster.Comments.COLS_4 };
        String selection = DatabaseMaster.Comments.COLS_2 + " LIKE?";
        String[] selection_args = {MovieName};
        Cursor cursor3 = sqLiteDatabase.query(TABLE_NAME3,projections,selection,selection_args,null,null,null);
        return cursor3;
    }
    public ArrayList<String> getUsers(String MovieName){
        //DatabaseMaster master = new DatabaseMaster();
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] MovieName2 = new String[1];
        MovieName2[0] = MovieName;
        Cursor cursor5 = db.query(DatabaseMaster.Comments.TABLE_NAME,
                new String[]{DatabaseMaster.Comments.COLS_4},
                DatabaseMaster.Comments.COLS_2+ "=?",
                null,
                null,
                null,
                null);

        while(cursor5.moveToNext()){
            list.add(cursor5.getString(cursor5.getColumnIndex("MovieComments")));
        }

        return list;


    }

    //List<> Movies = new List<>;
}
