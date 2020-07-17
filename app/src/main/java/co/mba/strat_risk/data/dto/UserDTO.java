package co.mba.strat_risk.data.dto;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public final class UserDTO {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("company_id")
    private Integer company_id;

    @Ignore
    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, String username, Integer company_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.company_id = company_id;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", company_id=" + company_id +
                '}';
    }
}
