package com.example.modelpaper2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {
    TextView MovieName,MovieYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        MovieName = findViewById(R.id.editTextMovieName3);
        MovieYear = findViewById(R.id.editTextMovieYear4);
    }

    public void addMovie(View view){
            DBHandler dbHelper = new DBHandler(this);
            long val = dbHelper.addMovie(MovieName.getText().toString(),MovieYear.getText().toString());
            if(val > 0){
                Toast.makeText(this,"Movie successfully inserted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MovieList.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Error in insertion of the movie",Toast.LENGTH_SHORT).show();
            }
            //Intent intent = new Intent(getApplicationContext(), ProfileManagement.class);
            //intent.putExtra("UserName2",UserName.getText().toString());
            //intent.putExtra("Password2",Password.getText().toString());
            //startActivity(intent);
        }
    }