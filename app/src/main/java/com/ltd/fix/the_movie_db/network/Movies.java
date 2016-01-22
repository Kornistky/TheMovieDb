package com.ltd.fix.the_movie_db.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movies {
    @SerializedName("page")
    @Expose
    private String page;

    @SerializedName("total_pages")
    @Expose
    private String total_pages;

    @SerializedName("total_results")
    @Expose
    private String total_results;

    @SerializedName("results")
    @Expose
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public String getPage() {
        return page;
    }

    public String getTotal_pages()   {
        return total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    @Override
    public String toString()
    {
        return "ListMovie [results = "+results+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }
}
