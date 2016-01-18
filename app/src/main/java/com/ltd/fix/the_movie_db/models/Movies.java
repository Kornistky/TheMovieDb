package com.ltd.fix.the_movie_db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movies {
//    @SerializedName("page")
//    @Expose
//    private String mPage;


    @SerializedName("results")
    @Expose
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }
}
