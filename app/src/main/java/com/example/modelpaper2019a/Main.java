package com.example.modelpaper2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    TextView UserName,Password;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    DBHandler databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        UserName = findViewById(R.id.editTextPersonName);
        Password = findViewById(R.id.editTextPassword);
    }

    public void RegisterUser(View view){
        DBHandler dbHelper = new DBHandler(this);
        long val = dbHelper.RegisterUser(UserName.getText().toString(),Password.getText().toString());
        if(val > 0){
            Toast.makeText(this,"Data successfully inserted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Error in insertion",Toast.LENGTH_SHORT).show();
        }
        //Intent intent = new Intent(getApplicationContext(), ProfileManagement.class);
        //intent.putExtra("UserName2",UserName.getText().toString());
        //intent.putExtra("Password2",Password.getText().toString());
        //startActivity(intent);
    }

    public void SignIn(View view) {
        DBHandler dbHelper = new DBHandler(this);
        String user = UserName.getText().toString();
        String pass = Password.getText().toString();
        databaseHelper = new DBHandler(getApplicationContext());
        db = databaseHelper.getReadableDatabase();

        Boolean checkpass = dbHelper.Login(user, pass);
        if (checkpass == true) {
                Toast.makeText(this, "Successful admin login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddMovie.class);
                startActivity(intent);
                //Toast.makeText(this, "Successful other login", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), MovieList.class);
                //startActivity(intent);
            //intent.putExtra("UserName", user);
            //intent.putExtra("Password", pass);
            //intent.putExtra("DOB", DOB);
            //intent.putExtra("Gender", Gender);
            //startActivity(intent);
        }
        else
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();

    }
}