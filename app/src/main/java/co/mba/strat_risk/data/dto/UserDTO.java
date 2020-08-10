package co.mba.strat_risk.data.dto;

public final class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String username;
    private Integer company_id;
    private String key;
    private String search;
    private String source;


    public UserDTO(Integer id, String name, String email, String username, Integer company_id, String key, String search, String source) {
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", company_id=" + company_id +
                ", key='" + key + '\'' +
                ", search='" + search + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
