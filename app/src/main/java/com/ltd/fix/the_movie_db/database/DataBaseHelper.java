package com.ltd.fix.the_movie_db.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ltd.fix.the_movie_db.network.Movie;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fix on 21.01.16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static DataBaseHelper sInstance;


    public interface DataBase {
        public static final String DATABASE_NAME = "movieDatabase";
        public static final int DATABASE_VERSION = 3;
    }

    public interface Movies {
        public static final String TABLE_NAME = "movies";
        public static final String _ID = "id";
        public static final String _TITLE = "title";
        public static final String _POSTER = "poster_path";
        public static final String _RELEASE_DATE = "release_date";
        public static final String _OVERVIEW = "overview";

    }

    private static final int PAGE_START_INDEX = 15;
    private static final int PAGE_END_INDEX = 30;

    public static synchronized DataBaseHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DataBaseHelper(Context context) {
        super(context, DataBase.DATABASE_NAME, null, DataBase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE" + Movies.TABLE_NAME +
                "(" +
                Movies._ID + " INTEGER PRIMARY KEY," +
                Movies._TITLE + " TEXT, " +
                Movies._POSTER + " TEXT, " +
                Movies._RELEASE_DATE + " TEXT, " +
                Movies._OVERVIEW + " TEXT" +
                ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EX " + Movies.TABLE_NAME);
            onCreate(db);
        }
    }

    public void addMovie(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(Movies._ID, Integer.parseInt(movie.getId()));
            values.put(Movies._TITLE, movie.getTitle());
            values.put(Movies._POSTER, movie.getPoster_path());
            values.put(Movies._RELEASE_DATE, movie.getRelease_date());
            values.put(Movies._OVERVIEW, movie.getOverview());

            db.insertOrThrow(Movies.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void addAllMovies(Collection<Movie> movieCollection) {
        for (Movie m : movieCollection) addMovie(m);
    }

    private List<Movie> getMovies(int page, boolean byPopularity) {

        List<Movie> movies = new ArrayList<>();
        String MOVIE_SELECT_QUERY = null;
        if (byPopularity) {
            MOVIE_SELECT_QUERY = String.format(Queries.QUERIE_SELECT_ALL_FROM_s_ORDER_BY_s, Movies.TABLE_NAME);
        } else {
            MOVIE_SELECT_QUERY = String.format(Queries.QUERIE_SELECT_ALL_FROM_s, Movies.TABLE_NAME);
        }

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                int count = 0;
                do {
                    if (count < getStartPages(page)) continue;
                    if (count > getEndPages(page)) break;
                    movies.add(getMovieFromCursor(cursor));
                    count++;
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return movies;
    }

    private int getStartPages(int page) {
        return page * PAGE_START_INDEX;
    }

    private int getEndPages(int page) {
        return page * PAGE_END_INDEX;
    }

    public List<Movie> getMoviesByPopularity(int page) {
        return getMovies(page, true);
    }

    public List<Movie> getMoviesToDescribe(int page) {
        return getMovies(page, false);
    }

    public List<Movie> searchByTitle(String query) {
        List<Movie> movies = new ArrayList<>();
        String MOVIE_SELECT_QUERY = String.format(Queries.QUERIE_SELECT_ALL_FROM_s_WHERE_s_LIKE_s, Movies.TABLE_NAME, Movies._TITLE, query + '%');

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst())
                do {
                    movies.add(getMovieFromCursor(cursor));
                } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return movies;
    }

    public Movie getMovieItem(String id) {
        Movie movie = null;
        String MOVIE_SELECT_QUERY = String.format(Queries.QUERIE_SELECT_ALL_FROM_s_WHERE_s_LIKE_s, Movies.TABLE_NAME, Movies._ID, id);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                movie = getMovieFromCursor(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return movie;
    }

    private Movie getMovieFromCursor(Cursor cursor) {
        return new Movie()
                .setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(Movies._ID))))
                .setPoster_path(cursor.getString(cursor.getColumnIndex(Movies._POSTER)))
                .setTitle(cursor.getString(cursor.getColumnIndex(Movies._TITLE)))
                .setRelease_date(cursor.getString(cursor.getColumnIndex(Movies._RELEASE_DATE)))
                .setOverview(cursor.getString(cursor.getColumnIndex(Movies._OVERVIEW)));
    }
}
