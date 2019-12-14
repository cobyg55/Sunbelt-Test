package com.sunbeltfactory.sunbelttest.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sunbeltfactory.sunbelttest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_news_photo)
    ImageView ivNewsPhoto;

    @BindView(R.id.tv_news_author)
    TextView tvAuthor;

    @BindView(R.id.tv_news_separator)
    TextView tvSeparator;

    @BindView(R.id.tv_news_published_date)
    TextView tvPublishedAt;

    @BindView(R.id.tv_news_title)
    TextView tvTitle;

    @BindView(R.id.tv_news_description)
    TextView tvDescription;

    @BindView(R.id.tv_news_content)
    TextView tvContent;

    String photo, author, date, title, description, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle("");

        fetchExtras();

        configureView();
    }

    public void fetchExtras() {
        photo = getIntent().getStringExtra("photo");
        author = getIntent().getStringExtra("author");
        date = getIntent().getStringExtra("date");
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
    }

    public void configureView() {
        tvAuthor.setText(author);
        tvPublishedAt.setText(date);
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvContent.setText(content);

        if (photo != null && !photo.isEmpty()) {
            Glide.with(this).load(photo).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    ivNewsPhoto.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    ivNewsPhoto.setVisibility(View.VISIBLE);
                    return false;
                }
            }).into(ivNewsPhoto);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
