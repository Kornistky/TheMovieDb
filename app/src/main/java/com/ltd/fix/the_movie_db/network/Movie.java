package com.ltd.fix.the_movie_db.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {
    private static final String IMG_PATH = "http://image.tmdb.org/t/p/w320";

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    @SerializedName("id")
    @Expose
    private  Integer mId;


    public String getTitle() {
        return mTitle;
    }

    public String getImagePath() {
        return IMG_PATH + mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public Integer getId() {
        return mId;
    }
}
