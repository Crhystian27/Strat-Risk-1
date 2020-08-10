package co.mba.strat_risk.util;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.data.entity.User;


public class AppPreferences {
    private static AppPreferences instance;

    private AccessTokenDTO accessTokenDTO = null;
    private User user = null;

    private AppPreferences() {
    }

    public static synchronized AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        return instance;
    }

    public User getUser() {
        if (user == null) {
            user = Preferences.getObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    User.class);
        }
        return user;
    }

    public AccessTokenDTO getAccessTokenDTO() {
        if (accessTokenDTO == null) {
            accessTokenDTO = Preferences.getObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.AUTHORIZATION,
                    Constants.KEY_AUTHORIZATION,
                    AccessTokenDTO.class);

        }
        return accessTokenDTO;
    }

    public void setUser(User user) {
        if (user != null) {
            Preferences.setObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    user);
        }
        this.user = user;
    }

    public void setAccessTokenDTO(AccessTokenDTO accessTokenDTO) {
        if (accessTokenDTO != null) {
            Preferences.setObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.AUTHORIZATION,
                    Constants.KEY_AUTHORIZATION,
                    accessTokenDTO);
        }
        this.accessTokenDTO = accessTokenDTO;
    }
}
