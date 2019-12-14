package com.sunbeltfactory.sunbelttest.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunbeltfactory.sunbelttest.R;
import com.sunbeltfactory.sunbelttest.adapters.NewsAdapter;
import com.sunbeltfactory.sunbelttest.data.clients.NewsClient;
import com.sunbeltfactory.sunbelttest.data.local.Article;
import com.sunbeltfactory.sunbelttest.data.local.News;
import com.sunbeltfactory.sunbelttest.data.remote.NewsAPIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    private static final int PAGE_SIZE = 10;

    @BindView(R.id.recycler_view_news)
    RecyclerView mRecyclerView;

    @BindView(R.id.news_progress)
    ProgressBar mProgressBar;

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int currentPage = 1;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    private List<Article> mDataSet;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
        loadNews(currentPage);
    }

    private void loadNews(int page) {
        final NewsAPIInterface client = NewsClient.createService(NewsAPIInterface.class);
        client.getNews("co", page, NewsFragment.PAGE_SIZE).enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                mProgressBar.setVisibility(View.GONE);
                isLoading = false;
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        List<Article> articles = response.body().getArticles();
                        int insertIndex = mDataSet.size();
                        mDataSet.addAll(insertIndex, articles);
                        isLastPage = response.body().getArticles().size() < PAGE_SIZE;
                        mAdapter.notifyItemRangeInserted(insertIndex, articles.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                isLoading = false;
            }
        });
    }

    private void loadMoreItems() {
        mProgressBar.setVisibility(View.VISIBLE);
        isLoading = true;
        currentPage += 1;
        loadNews(currentPage);
    }


    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mDataSet = new ArrayList<>();

        mAdapter = new NewsAdapter(getContext(), mDataSet);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };
}