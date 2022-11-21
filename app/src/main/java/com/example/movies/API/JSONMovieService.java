package com.example.movies.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONMovieService {
    @GET("/?apikey=7d64bd60")
    Call<MovieData> getMovies(@Query("s") String tittle);
}
