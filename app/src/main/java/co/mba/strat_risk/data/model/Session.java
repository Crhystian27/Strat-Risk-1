package co.mba.strat_risk.data.model;


import com.google.gson.annotations.SerializedName;

public final class Session {

    @SerializedName("grant_type")
    private String grantType;
    @SerializedName("client_id")
    private Integer clientId;
    @SerializedName("client_secret")
    private String clientSecret;
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;

    public Session(String grantType, Integer clientId, String clientSecret, String userName, String password) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userName = userName;
        this.password = password;
    }
}
