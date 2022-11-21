package Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.MainActivity;
import com.example.movies.R;
import com.example.movies.database.MovieDB;
import com.example.movies.movieAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedMovies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedMovies extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView movieView;
    private movieAdapter movieViewR;
    private MovieDB movieDB;
    public MainActivity mainActivity;
    public Context ctx;
    public SavedMovies() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SavedMovies.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedMovies newInstance(String param1, String param2) {
        SavedMovies fragment = new SavedMovies();
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
        return inflater.inflate(R.layout.fragment_saved_movies, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        movieViewR.movies=movieDB.movieRead();
        movieView.setAdapter(movieViewR);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        movieDB = new MovieDB(ctx);
        movieView = (RecyclerView)view.findViewById(R.id.movie);
        movieViewR = new movieAdapter(ctx,mainActivity,2);
        movieViewR.movies=movieDB.movieRead();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        movieView.setLayoutManager(linearLayoutManager);
        movieView.setAdapter(movieViewR);
    }

}