package com.ltd.fix.the_movie_db.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.fix.the_movie_db.MainActivity;
import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Movie> mData;
    Context mContext;

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_text)
        TextView mTextView;

        @Bind(R.id.item_img)
        ImageView imageView;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }

        public void bind(Movie movie){
            mTextView.setText(movie.getTitle());
            Picasso.with(mContext)
                    .load(movie.getImagePath())
                    .resize(200, 200)
                    .centerCrop()
                    .into(imageView);
        }
    }


    public MyAdapter(Context context, List<Movie> Data){
        mData=Data;
        mContext=context;
    }


}
