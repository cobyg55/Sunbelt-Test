package com.sunbeltfactory.sunbelttest.data.local;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class News {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private List<Article> articles = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
