package co.mba.strat_risk.util;

import co.mba.strat_risk.base.BaseApplication;
import co.mba.strat_risk.data.dto.AccessTokenDTO;


public class AppPreferences {
    private static AppPreferences instance;

    private AccessTokenDTO accessTokenDTO = null;
    //private UserDT userDT = null;

    private AppPreferences() {
    }

    public static synchronized AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        return instance;
    }

    /*public UserDT userDT() {
        if (userDT == null) {
            userDT = Preferences.getObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    UserDT.class);
        }
        return userDT;
    }

    public void setUserDT(UserDT userDT) {
        if (userDT != null) {
            Preferences.setObject(
                    BaseApplication.getAppContext(),
                    Preferences.PreferenceType.USER,
                    Constants.KEY_USER,
                    userDT);
        }
        this.userDT = userDTO;
    }*/

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
