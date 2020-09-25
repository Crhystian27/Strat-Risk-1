package co.mba.strat_risk.data.dto;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


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
    private Pagemap pagemap;


    public static final class CseImage {

        @SerializedName("src")
        public String src;

        public CseImage() {
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        @Override
        public String toString() {
            return "CseImage{" +
                    "src='" + src + '\'' +
                    '}';
        }
    }

    public static final class Pagemap {

        @SerializedName("cse_image")
        public List<CseImage> cse_image;

        public Pagemap() {
        }

        public List<CseImage> getCse_image() {
            return cse_image;
        }

        public void setCse_image(List<CseImage> cse_image) {
            this.cse_image = cse_image;
        }

        @Override
        public String toString() {
            return "Pagemap{" +
                    "cse_image=" + cse_image +
                    '}';
        }
    }


    public ItemsDTO(String kind, String title, String snippet, String link, Pagemap pagemap) {
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

    public Pagemap getPagemap() {
        return pagemap;
    }

    public void setPagemap(Pagemap pagemap) {
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


