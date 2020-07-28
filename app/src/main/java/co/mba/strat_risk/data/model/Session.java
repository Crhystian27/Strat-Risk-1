package co.mba.strat_risk.data.model;

public final class Session {

    private String grantType;

    private Integer clientId;

    private String clientSecret;

    private String userName;

    private String password;


    public Session(String grantType, Integer clientId, String clientSecret, String userName, String password) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userName = userName;
        this.password = password;
    }
}
