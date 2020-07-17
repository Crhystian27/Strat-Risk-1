package co.mba.strat_risk.data.model;

import androidx.room.Ignore;

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

    public Session() {
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
