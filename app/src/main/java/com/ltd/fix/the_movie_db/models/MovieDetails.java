package com.ltd.fix.the_movie_db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieDetails {

    @SerializedName("original_title")
    @Expose
    private String mTitle;

    @SerializedName("overview")
    @Expose
    private String mOverview;

    @SerializedName("tagline")
    @Expose
    private String mTagLine;

    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;

    @SerializedName("genres")
    @Expose
    private List<Genre> mGenres;

    @SerializedName("homepage")
    @Expose
    private String mHomepageLink;

    public String getTitle() {
        return mTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getTagLine() {
        return mTagLine;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public static class Genre{
        @SerializedName("name")
        @Expose
        private String mName;

        public String getName() {
            return mName;
        }
    }
}
