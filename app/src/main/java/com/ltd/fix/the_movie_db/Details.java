package com.ltd.fix.the_movie_db;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ltd.fix.the_movie_db.models.Movie;
import com.ltd.fix.the_movie_db.models.MovieDetails;
import com.ltd.fix.the_movie_db.models.RestClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Details extends AppCompatActivity {
    public static final String ID_PARAM = "id_param";
    Context mContext;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
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

    @Bind(R.id.movie_title)
    TextView mMovieTitle;

    @Bind(R.id.movie_overview)
    TextView mMovieOverview;

    @Bind(R.id.tagline)
    TextView mMovieTagLine;

    @Bind(R.id.item_act)
    ImageView imageView;

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
        Picasso.with(mContext)
                .load(movieDetails.getImagePath())
                .resize(250,250)
                .centerCrop()
                .into(imageView);
    }
}
