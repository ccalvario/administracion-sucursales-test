package prj.ccalvario.administracionsucursales.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.Build;

import java.util.List;

import prj.ccalvario.administracionsucursales.utils.SecureData;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.db.AppDatabase;
import prj.ccalvario.administracionsucursales.db.UsuarioDao;
import prj.ccalvario.administracionsucursales.model.Usuario;

public class UsuarioRepository {

    private UsuarioDao mUsuarioDao;
    private String mSecretKey;

    public UsuarioRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mUsuarioDao = db.usuarioDao();
        mSecretKey = Build.DEVICE + Build.ID + Build.USER;
    }

    public LiveData<List<Usuario>> getAllUsuarios() {
        return mUsuarioDao.getAllUsuarios();
    }

    public LiveData<Usuario> getUsuario(int id) { return mUsuarioDao.getUsuario(id); }

    public LiveData<Usuario> getUsuarioByEmail(String email) { return mUsuarioDao.getUsuarioByEmail(email); }

    public LiveData<Usuario> getUsuarioByEmailPassword(String email, String password) {
        SecureData sec =  new SecureData(mSecretKey, true);
        String value = sec.encryptValue(password);
        return mUsuarioDao.getUsuarioByEmailPassword(email, value);
    }

    public void insert (Usuario usuario) {
        SecureData sec =  new SecureData(mSecretKey, true);
        String value = sec.encryptValue(usuario.getPassword());
        usuario.setPassword(value);
        new insertAsyncTask(mUsuarioDao).execute(usuario);
    }

    public void update (Usuario sucursal) { new updateAsyncTask(mUsuarioDao).execute(sucursal); }

    private static class insertAsyncTask extends AsyncTask<Usuario, Void, Void> {

        private UsuarioDao mAsyncTaskDao;

        insertAsyncTask(UsuarioDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Usuario... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Usuario, Void, Void> {

        private UsuarioDao mAsyncTaskDao;

        updateAsyncTask(UsuarioDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Usuario... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
