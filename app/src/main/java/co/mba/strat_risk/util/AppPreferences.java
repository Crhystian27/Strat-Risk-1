package co.mba.strat_risk.util;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.data.dto.UserDTO;

public class AppPreferences {
    private static AppPreferences instance;

    private AccessTokenDTO accessTokenDTO = null;
    private UserDTO userDTO = null;

    private AppPreferences() {
    }

    public static synchronized AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        return instance;
    }

    public UserDTO userDTO() {
        if (userDTO == null) {
            userDTO = Preferences.getObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    UserDTO.class);
        }
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        if (userDTO != null) {
            Preferences.setObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    userDTO);
        }
        this.userDTO = userDTO;
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
