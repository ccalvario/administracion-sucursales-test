package prj.ccalvario.administracionsucursales.utils;

import trikita.log.Log;

import prj.ccalvario.administracionsucursales.model.Usuario;

public class SessionManager {

    private static SessionManager sInstance;
    private Usuario mUsuario;

    public static SessionManager getInstance() {
        if(sInstance == null) {
            sInstance = new SessionManager();
        }

        return sInstance;
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
    }
}
