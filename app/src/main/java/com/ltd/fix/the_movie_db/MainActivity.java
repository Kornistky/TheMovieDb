package com.ltd.fix.the_movie_db;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import com.ltd.fix.the_movie_db.adapters.FilmsPagerAdapter;
import com.ltd.fix.the_movie_db.fragments.FilmsListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FilmsListFragment.OnFragmentInteractionListener {

    @Bind(R.id.pager)
    ViewPager mViewPager;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentManager fm = getSupportFragmentManager();

        FilmsPagerAdapter adapter = new FilmsPagerAdapter(this, fm);
        mViewPager.setAdapter(adapter);
        setSupportActionBar(toolbar);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.popular_films));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.top_rated_films));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.upcoming_films));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_item_search:
                onSearchRequested();
                break;
            default: break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
