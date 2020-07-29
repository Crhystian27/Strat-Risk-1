package co.mba.strat_risk.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_table", indices = {@Index(value = "id", unique = true)})
public final class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @ColumnInfo(name = "email")
    @SerializedName("email")
    private String email;
    @ColumnInfo(name = "username")
    @SerializedName("username")
    private String username;
    @ColumnInfo(name = "company_id")
    @SerializedName("company_id")
    private Integer company_id;
    @ColumnInfo(name = "key")
    @SerializedName("key")
    private String key;
    @ColumnInfo(name = "search")
    @SerializedName("search")
    private String search;
    @ColumnInfo(name = "source")
    @SerializedName("source")
    private String source;

    @Ignore
    public User() {
    }

    public User(Integer id, String name, String email, String username, Integer company_id, String key, String search, String source) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.company_id = company_id;
        this.key = key;
        this.search = search;
        this.source = source;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
