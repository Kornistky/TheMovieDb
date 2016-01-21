package com.ltd.fix.the_movie_db.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movies {
    @SerializedName("page")
    @Expose
    private String mPage;

    @SerializedName("total_pages")
    @Expose
    private String total_pages;

    @SerializedName("total_results")
    @Expose
    private String total_results;


    @SerializedName("results")
    @Expose
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public String getPage() {
        return mPage;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    @Override
    public String toString()
    {
        return "ListMovie [results = "+mMovies+", page = "+mPage+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }

    public String getTotal_results() {
        return total_results;
    }
}
