package com.ltd.fix.the_movie_db.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by fix on 18.01.16.
 */
public interface TheMovieDbService {
    //https://api.themoviedb.org/3/movie/550?api_key=70975a712a50f1825d37028d9a9d58fb

    @GET("/3/movie/popular")
    Call<Movies> getPopularMovies(@Query("api_key") String apiKey);

    @GET("/3/movie/top_rated")
    Call<Movies> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("/3/movie/upcoming")
    Call<Movies> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("/3/search/movie")
    Call<Movies> searchMovies(@Query("api_key") String apiKey, @Query("query") String searchQuery);

    @GET("/3/movie/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") Integer movieId, @Query("api_key") String apiKey);
}
