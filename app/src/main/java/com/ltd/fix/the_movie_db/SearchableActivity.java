package com.ltd.fix.the_movie_db;

import android.app.SearchManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ltd.fix.the_movie_db.fragments.FilmsListFragment;
import com.ltd.fix.the_movie_db.network.MoviesRequestType;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchableActivity extends AppCompatActivity implements FilmsListFragment.OnFragmentInteractionListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        ButterKnife.bind(this);
        FragmentManager fm = getSupportFragmentManager();


        String query = getIntent().getStringExtra(SearchManager.QUERY);

        Fragment fragment = fm.findFragmentById(R.id.searchable_content);
        if (fragment == null) {
            fragment = FilmsListFragment.newInstance(query,"",MoviesRequestType.SEARCH);
            fm.beginTransaction()
                    .add(R.id.searchable_content, fragment)
                    .commit();
        }


        setSupportActionBar(toolbar);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
