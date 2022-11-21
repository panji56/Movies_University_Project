package Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movies.API.CallAPI;
import com.example.movies.API.JSONMovieService;
import com.example.movies.API.MovieData;
import com.example.movies.API.MovieDetail;
import com.example.movies.API.Search;
import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.data.Movies;
import com.example.movies.movieAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchMovies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchMovies extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchMovies() {
        // Required empty public constructor
    }

    public MainActivity mainActivity;
    public Context ctx;
    private EditText searchmovie;
    private List<MovieDetail> movieList;
    private RecyclerView movieView;
    private movieAdapter movieViewR;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchMovies.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchMovies newInstance(String param1, String param2) {
        SearchMovies fragment = new SearchMovies();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        Button searchbutton = (Button) view.findViewById(R.id.search_button);
        searchmovie= (EditText) view.findViewById(R.id.search_movie);
        movieView = (RecyclerView) view.findViewById(R.id.movie);
        movieViewR = new movieAdapter(ctx,mainActivity,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        movieView.setLayoutManager(linearLayoutManager);
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search=searchmovie.getText().toString();
                Retrofit retrofit = CallAPI.getRetrofit();
                JSONMovieService service = retrofit.create(JSONMovieService.class);
                Call<MovieData> call = service.getMovies(search);
                call.enqueue(new Callback<MovieData>() {
                    @Override
                    public void onResponse(Call<MovieData> call,Response<MovieData> response) {
                        if (response.isSuccessful()) {
                            Log.i("MSG", ""+response.message());
                            ArrayList<Movies> mvs=new ArrayList<Movies>();
                            try {
                                List<Search> sc = response.body().getSearch();
                                Log.i("OBJDATA",""+sc);
                                for(int i=0;i<sc.size();i++){
                                    Movies mv = new Movies();
                                    mv.imdbID=sc.get(i).getImdbID();
                                    mv.Title=sc.get(i).getTitle();
                                    mv.poster=sc.get(i).getPoster();
                                    mv.Year=sc.get(i).getYear();
                                    mvs.add(mv);
                                    Log.i("OBJMV",""+mv);
                                    Log.i("OBJMVS",""+mvs);
                                }
                                movieViewR.movies=mvs;
                                movieView.setAdapter(movieViewR);
                                Log.i("JSON", "it works");
                                }catch (Exception e){
                                    Toast.makeText(ctx,"Title search not found",Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                        };
                        Log.i("JSON", "it works");
                    };
                    @Override
                    public void onFailure(Call<MovieData> call, Throwable t) {
                        //Log.i("TAG", "onFailure: failed to connect");;
                        Log.i("TAG","failed");
                        t.printStackTrace();
                    }
                });
            }
        });


    }

}