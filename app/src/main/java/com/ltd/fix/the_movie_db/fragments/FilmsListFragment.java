package com.ltd.fix.the_movie_db.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.adapters.MyAdapter;
import com.ltd.fix.the_movie_db.models.Movie;
import com.ltd.fix.the_movie_db.models.MovieDetails;
import com.ltd.fix.the_movie_db.models.RestClient;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;



public class FilmsListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Bind(R.id.rv)
    RecyclerView mRecyclerView;

    public FilmsListFragment() {
    }

    private void Init(){

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setHasFixedSize(true);

        RestClient restClient = new RestClient();
        restClient.addListener(new RestClient.Listener() {
            @Override
            public void onFilmsLoaded(List<Movie> movies) {
                mRecyclerView.setAdapter(new MyAdapter(getActivity(), movies));
            }

            @Override
            public void onMovieDetailsLoaded(MovieDetails movieDetails) {
            }
        });
    }

    public static FilmsListFragment newInstance(String param1, String param2) {
        FilmsListFragment fragment = new FilmsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
