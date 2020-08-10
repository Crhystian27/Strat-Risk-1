package co.mba.strat_risk.data.dto;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.mba.strat_risk.data.entity.News;

public final class NewsDTO {

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private List<News> articles;


    public NewsDTO() {
    }


    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
