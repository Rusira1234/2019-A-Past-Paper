package com.example.modelpaper2019a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieOverView extends AppCompatActivity {
    ListView CommentListView;
    TextView TextAverageRating, MovieName,CommentValue;
    EditText RatingValue;
    Button SubmitButton;
    Intent Yeah;
    float value1,value2;
    int count = 0;
    float avg;
    String value,MovieName2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_over_view);
        CommentListView = findViewById(R.id.CommentListView);
        TextAverageRating = findViewById(R.id.textView8);
        MovieName = findViewById(R.id.textView3);
        RatingValue = findViewById(R.id.RatingValue);
        CommentValue = findViewById(R.id.CommentValue);
        SubmitButton = findViewById(R.id.SubmitButton);
        DBHandler dbHelper = new DBHandler(this);
        //Cursor cursor = dbHelper.ViewComment();
        StringBuilder stringBuilder = new StringBuilder();
        Yeah = getIntent();
        MovieName.setText(Yeah.getStringExtra("MovieName"));
        db = dbHelper.getReadableDatabase();
        //Cursor cursor2 = dbHelper.getComment(MovieName.getText().toString(),db);
        /*if(cursor2.moveToNext())
        {
            String Comment = cursor2.getString(cursor2.getColumnIndex("MovieComments"));
            String Comment2 = cursor2.getString(cursor2.getColumnIndex("MovieComments"));
            stringBuilder.append("\nMovieComment: " + Comment);
            //String Date = cursor.getString(cursor.getColumnIndex("Date"));
            //String Time = cursor.getString(cursor.getColumnIndex("Time"));
            //TextTitleName.setText(Title);
            //editTextDate2.setText(Date);
            //editTextTime2.setText(Time);

        }*/
        /*while (cursor.moveToNext()) {
            MovieName2 = cursor.getString(1);
            if(MovieName2 == MovieName.getText().toString()){
                stringBuilder.append("\nMovieComment: " + cursor.getString(3) + "\n");
            }

            //count++;
            try {
                MovieName2 = cursor.getString(1);
                value = cursor.getString(2);
                if(Float.parseFloat(value) >= 0 && MovieName2.equals(MovieName)){
                    count++;
                }
                value1 = Float.parseFloat(value);
                value2 = value2 + value1;
            }catch(NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        }*/
        avg = value2 / count;

        //TextAverageRating.setText(Float.toString(avg.getText()));
        /*while (cursor.moveToNext()) {
            stringBuilder2.append("\nMovieRating: " + cursor.getString(2) + "\n");
        }*/
        //final ArrayList<String> arr_list = new ArrayList<String>();
        //String str = stringBuilder.toString();
        TextAverageRating.setText(Float.toString(avg));//Float.toString(avg);
        //TextAverageRating.setText(stringBuilder2);

        //String[] str1 = str.split("\n");
        /*for (int i = 0; i < str1.length; i++) {

            arr_list.add(str1[i]);
        }*/

        //System.out.println("values=="+str1);
        //System.out.println("arr_list=="+arr_list);

        ArrayList<String> list = dbHelper.getUsers(MovieName.getText().toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        // Assign adapter to ListView

        CommentListView.setAdapter(adapter);



        /*MovieListID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MovieOverView.class);
                intent.putExtra("MovieName", arr_list.get(position));
            }
        });*/
        //MovieListID.setText(stringBuilder);
    }

    public void addComment(View view) {
        DBHandler dbHelper = new DBHandler(this);
        long val = dbHelper.insertComment(MovieName.getText().toString(),RatingValue.getText().toString(), CommentValue.getText().toString());
        if (val > 0) {
            Toast.makeText(this, "Comment successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error in insertion of the Comment", Toast.LENGTH_SHORT).show();
        }
    }
}