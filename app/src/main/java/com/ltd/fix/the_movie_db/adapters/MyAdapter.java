package com.ltd.fix.the_movie_db.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.ltd.fix.the_movie_db.UI.Details;
import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.network.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Movie> mData;
    Context mContext;
    int width = 180, height = 180;

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Fresco.initialize(mContext);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
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
        SimpleDraweeView simpleDraweeView;

        @Bind(R.id.release_date)
        TextView mReleaseDate;

        @Bind(R.id.film_overview)
        TextView mOverview;

        private int mId;

        @Nullable
        @OnClick(R.id.card_view)
        void onCardClicked() {
            Intent intent = new Intent(mContext, Details.class);
            intent.putExtra(Details.ID_PARAM, mId);
            mContext.startActivity(intent);
        }

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void bind(Movie movie) {
            mTextView.setText(movie.getTitle());
            mOverview.setText(movie.getOverview());
            mReleaseDate.setText(movie.getReleaseDate());
            mId = movie.getId();


            ImageRequest request = ImageRequestBuilder
                    .newBuilderWithSource(Uri.parse(movie.getImagePath()))
                    .setResizeOptions(new ResizeOptions(width, height))
                    .setAutoRotateEnabled(true)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        }
    }


    public MyAdapter(Context context, List<Movie> Data) {
        mData = Data;
        mContext = context;
    }
}
