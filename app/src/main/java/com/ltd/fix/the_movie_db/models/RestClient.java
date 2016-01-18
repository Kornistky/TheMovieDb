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
    private static final String TAG = "RestClient";
    private static final String API_KEY = "405c542223f61538699ff98f04ccb6c4";
    public static final String BASE_URL = "http://api.themoviedb.org";

    public interface Listener {
        void onFilmsLoaded(List<Movie> films);
    }

    List<Listener> mListeners = new ArrayList<>();

    public void addListener(Listener l) {
        mListeners.add(l);
    }

    public void removeListener(Listener l) {
        mListeners.remove(l);
    }


    TheMovieDbService mService;

    public RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(TheMovieDbService.class);
    }

    private Call getMoviesContent(MoviesRequestType moviesRequestType) {
        switch (moviesRequestType) {
            case POPULAR:
                return mService.getPopularMovies(API_KEY);
            case TOP_RATED:
                return mService.getTopRatedMovies(API_KEY);
            case UPCOMING:
                return mService.getUpcomingMovies(API_KEY);
            default:
                return mService.getPopularMovies(API_KEY);
        }
    }

    public void searchMovies(String searchQuery) {
        try {
            Call films = mService.searchMovies(API_KEY, searchQuery);
            enqueueMovies(films);
        } catch (Exception e) {
            String ee = e.getMessage();

        }
    }

    public void getMovies(MoviesRequestType moviesRequestType) {
        try {
            Call films = getMoviesContent(moviesRequestType);
            enqueueMovies(films);
        } catch (Exception e) {
            String ee = e.getMessage();
        }
    }

    private void enqueueMovies(Call moviesContent) {
        moviesContent.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Response<Movies> response, Retrofit retrofit) {
                Movies movies = response.body();
                if (movies == null)
                    return;
                List<Movie> myMovie = movies.getMovies();
                for (Listener l : mListeners)
                    l.onFilmsLoaded(myMovie);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        });
    }
}
