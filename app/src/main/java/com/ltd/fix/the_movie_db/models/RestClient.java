package com.ltd.fix.the_movie_db.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class RestClient {
    public static final String TAG = "RestClient";
    public static final String API_KEY = "70975a712a50f1825d37028d9a9d58fb";
    public static final String BASE_URL = "http://api.themoviedb.org";

    public interface Listener{
        void onFilmsLoaded(List<Movie> movies);
        void onMovieDetailsLoaded(MovieDetails movieDetails);
    }

    List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener l){
        mListeners.add(l);
    }

    public void removeListener(Listener l){
        mListeners.remove(l);
    }

    Retrofit mRetrofit;
    public RestClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
