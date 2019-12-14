package com.sunbeltfactory.sunbelttest.data.clients;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsClient {

    private static final String BASE_URL = "https://newsapi.org/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Interceptor requestInterceptor() {
        return chain -> {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + "379e549d0cd241059ac5b8aa0d67309f")
                    .build();
            return chain.proceed(request);
        };
    }

    public static <S> S createService(Class<S> serviceClass) {
        httpClient.addInterceptor(requestInterceptor());
        builder.client(httpClient.build());
        return builder.build().create(serviceClass);
    }

}
