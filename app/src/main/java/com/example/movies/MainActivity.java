package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import Fragments.SavedMovies;
import Fragments.SearchMovies;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //start tab host

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.moviemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search_movie:
                SearchMovies searchMovies = new SearchMovies();
                searchMovies.mainActivity=this;
                searchMovies.ctx=this;
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFrame,searchMovies)
                        .commit();
                return true;
            case R.id.saved_movies:
                SavedMovies savedMovies = new SavedMovies();
                savedMovies.mainActivity=this;
                savedMovies.ctx=this;
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.MainFrame,savedMovies)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}