package com.ltd.fix.the_movie_db.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.UI.Details;
import com.ltd.fix.the_movie_db.adapters.MyAdapter;
import com.ltd.fix.the_movie_db.network.Movie;
import com.ltd.fix.the_movie_db.network.MovieDetails;
import com.ltd.fix.the_movie_db.network.MoviesRequestType;
import com.ltd.fix.the_movie_db.network.RestClient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FilmsListFragment extends Fragment {

    private static final String MOVIE_TYPE = "movie_type";
    public static final String ARG_PARAM1="param_1";
    public static final String ARG_PARAM2="param_2";


    private String mParam1;
    private String mParam2;



    Context context;
    private OnFragmentInteractionListener mListener;

    private MoviesRequestType moviesRequestType;
    RestClient restClient;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;



    public FilmsListFragment() {
    }

    private void Init() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), Details.class);
                        intent.putExtra(Details.ID_PARAM, "");
                        startActivity(intent);
                    }
                })
        );
        restClient = RestClient.getInstance().initialize();
        restClient.addListener(new RestClient.Listener() {
            @Override
            public void onFilmsLoaded(List<Movie> movies) {
                mRecyclerView.setAdapter(new MyAdapter(getActivity(), movies));
            }

            @Override
            public void onMovieDetailsLoaded(MovieDetails movieDetails) {

            }
        });

        if (moviesRequestType != moviesRequestType.SEARCH)
            restClient.getMovies(moviesRequestType);
        else
            restClient.searchMovies(mParam1);
    }

    public static FilmsListFragment newInstance(String param1, String param2,MoviesRequestType moviesRequestType) {
        FilmsListFragment fragment = new FilmsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putSerializable(MOVIE_TYPE, moviesRequestType);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            moviesRequestType = (MoviesRequestType) getArguments().getSerializable(MOVIE_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        Init();
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
