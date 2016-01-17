package com.ltd.fix.the_movie_db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fix on 18.01.16.
 */
public class Movie {
    public static final String IMG_PATH = "http://image.tmdb.org/t/p/w342";

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("title")
    @Expose
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    public String getImagePath() {
        return IMG_PATH + mPosterPath;
    }
}
