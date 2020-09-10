package co.mba.strat_risk.data.dto;

import androidx.room.Ignore;


import com.google.gson.annotations.SerializedName;

public final class ItemsDTO {

    @SerializedName("kind")
    private String kind;

    @SerializedName("title")
    private String title;

    @SerializedName("snippet")
    private String snippet;

    @SerializedName("link")
    private String link;

    @SerializedName("pagemap")
    private PageMapDTO pagemap;


    public ItemsDTO(String kind, String title, String snippet, String link, PageMapDTO pagemap) {
        this.kind = kind;
        this.title = title;
        this.snippet = snippet;
        this.link = link;
        this.pagemap = pagemap;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public PageMapDTO getPagemap() {
        return pagemap;
    }

    public void setPagemap(PageMapDTO pagemap) {
        this.pagemap = pagemap;
    }

    @Override
    public String toString() {
        return "ItemsDTO{" +
                "kind='" + kind + '\'' +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                ", link='" + link + '\'' +
                ", pagemap=" + pagemap +
                '}';
    }
}
