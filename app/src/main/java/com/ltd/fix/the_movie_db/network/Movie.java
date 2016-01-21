package com.ltd.fix.the_movie_db.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;


    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    public Movie() {

    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_title = in.readString();
        poster_path = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public Movie setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Movie setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Movie setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Movie setRelease_date(String release_date) {
        this.release_date = release_date;
        return this;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public Movie setOriginal_title(String original_title) {
        this.original_title = original_title;
        return this;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Movie setPoster_path(String poster_path) {
        this.poster_path = poster_path;
        return this;
    }


    @Override
    public String toString() {
        return "Movie [backdrop_path = " + backdrop_path + ",id = " + id + ", title = " + title + ", overview = " + overview + ",release_date = " + release_date + ", original_title = " + original_title + ", poster_path = " + poster_path + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(id);
    }
}