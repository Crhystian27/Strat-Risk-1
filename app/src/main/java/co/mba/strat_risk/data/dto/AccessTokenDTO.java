package co.mba.strat_risk.data.dto;

import com.google.gson.annotations.SerializedName;

public final  class AccessTokenDTO {

    @SerializedName("token_type")
    private String type;
    @SerializedName("expires_in")
    private String expires;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("refresh_token")
    private String refreshToken;

    public AccessTokenDTO(String type, String expires, String accessToken, String refreshToken) {
        this.type = type;
        this.expires = expires;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "type='" + type + '\'' +
                ", expires='" + expires + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
