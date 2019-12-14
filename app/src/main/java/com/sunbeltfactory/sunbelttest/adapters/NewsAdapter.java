package com.sunbeltfactory.sunbelttest.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sunbeltfactory.sunbelttest.R;
import com.sunbeltfactory.sunbelttest.TimeUtils;
import com.sunbeltfactory.sunbelttest.data.local.Article;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private List<Article> mDataSet;

    public NewsAdapter(Context mContext, List<Article> mDataSet) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = mDataSet.get(position);
        long time = TimeUtils.stringToDate(article.getPublishedAt()).getTime();

        holder.tvSource.setText(article.getSource().getName());
        holder.tvAuthor.setText(article.getAuthor());
        holder.tvTitle.setText(article.getTitle());
        holder.tvPublishedAt.setText(TimeUtils.getTimeAgo(time, mContext));

        if (article.getUrlToImage() != null && !article.getUrlToImage().isEmpty()) {
            Glide.with(mContext).load(article.getUrlToImage()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    holder.ivArticlePhoto.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    holder.ivArticlePhoto.setVisibility(View.VISIBLE);
                    return false;
                }
            }).into(holder.ivArticlePhoto);

        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvSource, tvAuthor, tvTitle, tvPublishedAt;
        ImageView ivArticlePhoto;

        NewsViewHolder(View view) {
            super(view);

            tvSource = view.findViewById(R.id.tv_source);
            tvAuthor = view.findViewById(R.id.tv_author);
            tvTitle = view.findViewById(R.id.tv_title);
            tvPublishedAt = view.findViewById(R.id.tv_published_at);

            ivArticlePhoto = view.findViewById(R.id.iv_arcticle_photo);
        }
    }
}
