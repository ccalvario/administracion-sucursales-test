package prj.ccalvario.administracionsucursales.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.db.AppDatabase;
import prj.ccalvario.administracionsucursales.db.UsuarioDao;
import prj.ccalvario.administracionsucursales.model.Usuario;

public class UsuarioRepository {

    private UsuarioDao mUsuarioDao;

    public UsuarioRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mUsuarioDao = db.usuarioDao();
    }

    public LiveData<List<Usuario>> getAllUsuarios() {
        return mUsuarioDao.getAllUsuarios();
    }

    public LiveData<Usuario> getUsuario(int id) { return mUsuarioDao.getUsuario(id); }

    public void insert (Usuario usuario) { new insertAsyncTask(mUsuarioDao).execute(usuario); }

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