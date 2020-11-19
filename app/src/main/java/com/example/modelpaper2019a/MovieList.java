package com.example.modelpaper2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity {
    ListView MovieListID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        MovieListID = findViewById(R.id.MovieListID);

        DBHandler dbHelper = new DBHandler(this);
        Cursor cursor = dbHelper.ViewMovie();
        StringBuilder stringBuilder = new StringBuilder();
        while(cursor.moveToNext()){
            stringBuilder.append("\nMovieName: " +cursor.getString(1) + "\nMovieYear: " +cursor.getString(2)+"\n") ;
        }
        final ArrayList<String> arr_list= new ArrayList<String>();
        String str=stringBuilder.toString();

        String[] str1=str.split("\n");
        for(int i=0;i<str1.length;i++){

            arr_list.add(str1[i]);
        }

        //System.out.println("values=="+str1);
        //System.out.println("arr_list=="+arr_list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  arr_list);

        // Assign adapter to ListView
        MovieListID.setAdapter(adapter);

        MovieListID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MovieOverView.class);
                intent.putExtra("MovieName", arr_list.get(position));
                startActivity(intent);
            }
        });
        //MovieListID.setText(stringBuilder);
    }
}