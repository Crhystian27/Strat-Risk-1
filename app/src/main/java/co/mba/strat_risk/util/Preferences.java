package co.mba.strat_risk.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Preferences {

    public enum PreferenceType {
        AUTHORIZATION {
            public String getGroup() {
                return Constants.PREFERENCE_AUTHORIZATION;
            }
        },
        USER {
            public String getGroup() {
                return Constants.PREFERENCE_USER;
            }
        };

        public abstract String getGroup();
    };


    private static SharedPreferences getPreferences(Context context, PreferenceType preferenceType) {
        return context.getSharedPreferences(preferenceType.getGroup(), Context.MODE_PRIVATE);
    }

    public static void setValue(Context context, PreferenceType preferenceType, String key, String value) {
        getPreferences(context, preferenceType).edit().putString(key, value).apply();
    }

    public static String getString(Context context, PreferenceType preferenceType, String key) {
        return getPreferences(context, preferenceType).getString(key, "");
    }

    public static void setObject(Context context, PreferenceType preferenceType, String key, Object value) {
        if (value != null) {
            Gson gson = new Gson();
            String json = gson.toJson(value);
            getPreferences(context, preferenceType).edit().putString(key, json).apply();
        }
        else {
            getPreferences(context, preferenceType).edit().clear().apply();
        }
    }

    //
    public static <T> T getObject(Context context, PreferenceType preferenceType, String key, Class<T> classOfT) {
        Gson gson = new Gson();
        String json = getPreferences(context, preferenceType).getString(key, "");
        return gson.fromJson(json, classOfT);
    }

    public static void clearData(Context context, PreferenceType preferenceType) {
        getPreferences(context, preferenceType).edit().clear().apply();
    }
}
