package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.data.Movies;

import java.util.ArrayList;


public class movieAdapter extends RecyclerView.Adapter<movieAdapter.ViewHolder> {

    private Context ctx;
    private int mode;
    private MainActivity mainActivity;
    public ArrayList<Movies> movies;

//mode = 1 for save, mode = 2 for delete
    //save movie when it is not in DB
    public movieAdapter(Context ctx,MainActivity mainActivity,int mode) {
        this.ctx = ctx;
        this.mainActivity=mainActivity;
        this.mode=mode;
        //load data from DB, save to maincursor
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.movie_view,parent,false);
        return new ViewHolder(v);   //panggil tiap data disini
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies mv = new Movies();
        mv=movies.get(position);
        holder.mv=mv;
        String title=movies.get(position).Title;
        holder.movieTitle.setText(title);
        Glide.with(ctx).load(movies.get(position).poster).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public String getSafeSubtring(String s, int limit){
        if(!TextUtils.isEmpty(s)){
            if(s.length()>limit){
                return s.substring(0,limit)+" ...";
            }
        }
        return s;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView movieTitle;
        public ImageView moviePoster;
        public Movies mv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle=(TextView) itemView.findViewById(R.id.movie_title);
            moviePoster=(ImageView) itemView.findViewById(R.id.movie_image);
            //user pencet tiap recyclerView, diarahkan ke activity update
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //panggil activity untuk update
            Intent intent = new Intent(ctx,moviedetail.class);
            intent.putExtra("data",mv);
            intent.putExtra("mode",mode);
            mainActivity.startActivity(intent);
        }
    }
}
