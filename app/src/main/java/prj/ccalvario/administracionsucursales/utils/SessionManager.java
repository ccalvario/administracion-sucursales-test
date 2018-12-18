package prj.ccalvario.administracionsucursales.utils;

import android.content.Context;
import android.content.SharedPreferences;

import trikita.log.Log;

import prj.ccalvario.administracionsucursales.model.Usuario;

public class SessionManager {

    private static SessionManager sInstance;
    private Context mContext;
    private Usuario mUsuario;

    private final String PREF_NAME = "preferencias";
    private final String KEY_USERNAME = "key_username";
    private final String KEY_PASSWORD = "key_password";

    public static SessionManager getInstance() {
        if(sInstance == null) {
            sInstance = new SessionManager();
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

    public String getUsuarioId() {
        if(mUsuario != null) {
            return String.valueOf(mUsuario.getId());
        } else {
            return "";
        }
    }

    public void logOut() {
        mUsuario = null;
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_USERNAME, "");
        editor.putString(KEY_PASSWORD, "");
        editor.apply();
    }

    public void storeCredencials() {
        if(mUsuario != null) {
            SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(KEY_USERNAME, mUsuario.getEmail());
            editor.putString(KEY_PASSWORD, mUsuario.getPassword());
            editor.apply();
        }
    }

    public String getStoredUsername() {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        return pref.getString(KEY_USERNAME, "");
    }

    public String getStoredUPassword() {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, 0);
        return pref.getString(KEY_PASSWORD, "");
    }
}
