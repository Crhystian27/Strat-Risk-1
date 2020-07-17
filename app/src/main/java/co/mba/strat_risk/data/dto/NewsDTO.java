package co.mba.strat_risk.data.dto;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class NewsDTO {

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private List<ArticlesDTO> articles;


    public NewsDTO() {
    }


    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesDTO> articles) {
        this.articles = articles;
    }
}
