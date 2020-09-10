package co.mba.strat_risk.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_table", indices = {@Index(value = "id", unique = true)})
public final class News {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "kind")
    private String kind;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "snippet")
    private String snippet;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "src")
    private String src;

    // 3(risk) - 2(Interesting) - 1(opportunity) // 0 local
    @ColumnInfo(name = "status")
    private Integer status;

    /*@SerializedName("pagemap")
    @ColumnInfo(name = "pagemap")
    private Pagemap pagemap;*/

    @Ignore
    public News() {
    }

    public News(String kind, String title, String snippet, String link, String src, Integer status) {
        this.kind = kind;
        this.title = title;
        this.snippet = snippet;
        this.link = link;
        this.src = src;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

