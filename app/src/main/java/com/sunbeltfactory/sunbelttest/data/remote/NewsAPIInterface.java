package com.sunbeltfactory.sunbelttest.data.remote;

import com.sunbeltfactory.sunbelttest.data.local.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIInterface {

    @GET("v2/top-headlines")
    Call<News> getNews(@Query("country") String country,
                       @Query("page") int page,
                       @Query("pageSize") int pageSize);
}
