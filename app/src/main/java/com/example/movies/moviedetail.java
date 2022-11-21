package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movies.data.Movies;
import com.example.movies.database.MovieDB;

public class moviedetail extends AppCompatActivity {
    private TextView movieTitle,movieID,movieYear;
    private ImageView moviePoster;
    private Button detailbutton;
    private MovieDB movieDB;
    private Movies mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetail);
        movieDB = new MovieDB(this);
        movieTitle=(TextView) findViewById(R.id.movie_title);
        moviePoster=(ImageView) findViewById(R.id.movie_image);
        movieID=(TextView) findViewById(R.id.movie_ID);
        movieYear=(TextView) findViewById(R.id.movie_Year);
        detailbutton=(Button) findViewById(R.id.detail_button);
        Intent intent = getIntent();
        int mode=intent.getIntExtra("mode",0);
        mv = (Movies) intent.getSerializableExtra("data");
        movieTitle.setText(mv.Title);
        movieID.setText(mv.imdbID);
        movieYear.setText(mv.Year);
        Glide.with(this).load(mv.poster).into(moviePoster);
        if(mode==1){
            //save
            detailbutton.setText(R.string.Save_movie);
            detailbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int s=movieDB.movieRead(mv.imdbID).size();
                    if(s==0){
                        movieDB.movieInsert(mv);
                    }else{
                        Toast.makeText(moviedetail.this,"Movie already saved",Toast.LENGTH_LONG).show();
                    }
                    finish();
                }
            });
        }else if(mode==2){
            //delete
            detailbutton.setText(R.string.delete_movie);
            detailbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieDB.movieDelete(mv.imdbID);
                    finish();
                }
            });
        }
    }
}