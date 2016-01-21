package com.ltd.fix.the_movie_db;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ltd.fix.the_movie_db.network.Movie;
import com.ltd.fix.the_movie_db.network.MovieDetails;
import com.ltd.fix.the_movie_db.network.RestClient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Details extends AppCompatActivity {
    public static final String ID_PARAM = "id_param";
//    Context mContext;
    @Bind(R.id.toolbar)
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        if (toolbar != null) {
            setTitle("Детали фильма");
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        init();
    }

    private void init() {
        Integer id = getIntent().getIntExtra(ID_PARAM, -1);
        RestClient restClient = new RestClient();
        restClient.addListener(new RestClient.Listener() {
            @Override
            public void onFilmsLoaded(List<Movie> movies) {

            }

            @Override
            public void onMovieDetailsLoaded(MovieDetails movieDetails) {
                detailsInit(movieDetails);
            }
        });

        restClient.getMovieDetails(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @Bind(R.id.movie_title)
    TextView mMovieTitle;

    @Bind(R.id.movie_overview)
    TextView mMovieOverview;

    @Bind(R.id.tagline)
    TextView mMovieTagLine;

    @Bind(R.id.item_act)
    SimpleDraweeView mDrawerView;

    @Bind(R.id.data)
    TextView mDate;

    @Bind(R.id.language)
    TextView mLanguage;



    private void detailsInit(MovieDetails movieDetails){
        mMovieTitle.setText(movieDetails.getTitle());
        mMovieOverview.setText(movieDetails.getOverview());
        mMovieTagLine.setText(movieDetails.getTagLine());
        mDate.setText(movieDetails.getReleaseDate());
        mLanguage.setText(movieDetails.getOriginalLanguage());
        mDrawerView.setImageURI(Uri.parse(movieDetails.getImagePath()));

    }
}
