package co.mba.strat_risk.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_table")
public final class News {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "urlToImage")
    private String urlToImage;
    @ColumnInfo(name = "publishedAt")
    private String publishedAt;

    // -1(risk) - 0 (Interesting) - 1 (opportunity)
    @ColumnInfo(name = "status")
    private Integer status;

    @Ignore
    public News() {
    }

    public News(Integer id, String name, String title, String description, String content, String urlToImage, String publishedAt, Integer status) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.content = content;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
