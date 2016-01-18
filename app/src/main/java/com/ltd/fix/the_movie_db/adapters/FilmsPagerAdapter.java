package com.ltd.fix.the_movie_db.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.fragments.FilmsListFragment;


public class FilmsPagerAdapter extends FragmentPagerAdapter {
    public static  int items = 3;

    private Context mContext;

    public FilmsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return FilmsListFragment.newInstance("","");
    }

    @Override
    public int getCount() {
        return items;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0 :
                return mContext.getString(R.string.popular_films);
            case 1:
                return mContext.getString(R.string.top_rated_films);
            case 2:
                return mContext.getString(R.string.upcoming_films);
            default:
                return "";
        }
    }
}
