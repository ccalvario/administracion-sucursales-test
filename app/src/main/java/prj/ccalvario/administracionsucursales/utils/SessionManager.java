package prj.ccalvario.administracionsucursales.utils;

import android.content.Context;
import android.os.Build;

import trikita.log.Log;

import prj.ccalvario.administracionsucursales.model.Usuario;

public class SessionManager {

    private static SessionManager sInstance;
    private Context mContext;
    private Usuario mUsuario;
    private String mSecretKey;

    private final String PREF_NAME = "preferencias";
    private final String KEY_USERNAME = "key_username";
    private final String KEY_PASSWORD = "key_password";

    public static SessionManager getInstance() {
        if(sInstance == null) {
            sInstance = new SessionManager();
            sInstance.mSecretKey = Build.USER + Build.DEVICE + Build.ID;
        }

        return sInstance;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setUsuario(Usuario usuario) {
        mUsuario = usuario;
    }

    public Usuario getUsuario() {
        return mUsuario;
    }

    public void logOut() {
        SecurePreferences preferences = new SecurePreferences(mContext, PREF_NAME, mSecretKey, true);
        preferences.clear();
    }

    public void storeCredencials(String username, String password) {
        SecurePreferences preferences = new SecurePreferences(mContext, PREF_NAME, mSecretKey, true);
        preferences.put(KEY_USERNAME, username);
        preferences.put(KEY_PASSWORD, password);
    }

    public String getStoredUsername() {
        SecurePreferences preferences = new SecurePreferences(mContext, PREF_NAME, mSecretKey, true);
        return preferences.getString(KEY_USERNAME);
    }

    public String getStoredUPassword() {
        SecurePreferences preferences = new SecurePreferences(mContext, PREF_NAME, mSecretKey, true);
        return preferences.getString(KEY_PASSWORD);
    }
}
