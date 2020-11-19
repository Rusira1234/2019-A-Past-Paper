package com.example.modelpaper2019a;

import android.provider.BaseColumns;

public class DatabaseMaster {


    public DatabaseMaster(){

    }

    public class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLS_1 = "UserID";
        public static final String COLS_2 = "UserName";
        public static final String COLS_3 = "Password";
        public static final String COLS_4 = "UserType";
    }

    public class Movie implements BaseColumns {
        public static final String TABLE_NAME = "movies";
        public static final String COLS_1 = "MovieID";
        public static final String COLS_2 = "MovieName";
        public static final String COLS_3 = "MovieYear";
    }

    public class Comments implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COLS_1 = "CommentID";
        public static final String COLS_2 = "MovieName";
        public static final String COLS_3 = "MovieRating";
        public static final String COLS_4 = "MovieComments";
    }
}
