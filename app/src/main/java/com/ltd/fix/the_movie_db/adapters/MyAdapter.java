package com.ltd.fix.the_movie_db.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.fix.the_movie_db.Details;
import com.ltd.fix.the_movie_db.MainActivity;
import com.ltd.fix.the_movie_db.R;
import com.ltd.fix.the_movie_db.models.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

        @Bind(R.id.release_date)
        TextView mReleaseDate;

        @Bind(R.id.film_overview)
        TextView mOverview;

        private int mId;

        @Nullable
        @OnClick(R.id.card_view) void onCardClicked(){
            Intent intent = new Intent(mContext, Details.class);
            intent.putExtra(Details.ID_PARAM, mId);
            mContext.startActivity(intent);
        }

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }

        public void bind(Movie movie){
            mTextView.setText(movie.getTitle());
            mOverview.setText(movie.getOverview());
            mReleaseDate.setText(movie.getReleaseDate());
            mId = movie.getId();
            Picasso.with(mContext)
                    .load(movie.getImagePath())
                    .resize(200, 200)
                    .transform(new CircleTransform())
                    .centerCrop()
                    .into(imageView);
        }
    }


    public MyAdapter(Context context, List<Movie> Data){
        mData=Data;
        mContext=context;
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

}
