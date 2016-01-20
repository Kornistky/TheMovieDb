package com.ltd.fix.the_movie_db.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieDetails {
    private static final String IMG_PATH = "http://image.tmdb.org/t/p/w320";

    @SerializedName("original_title")
    @Expose
    private String mTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("tagline")
    @Expose
    private String mTagLine;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("homepage")
    @Expose
    private String mHomepageLink;

    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;

    public String getTitle() {
        return mTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getImagePath() {
        return IMG_PATH + mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getTagLine() {
        return mTagLine;
    }


}