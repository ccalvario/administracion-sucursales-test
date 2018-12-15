package prj.ccalvario.administracionsucursales.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.db.AppDatabase;
import prj.ccalvario.administracionsucursales.db.SucursalDao;
import prj.ccalvario.administracionsucursales.model.Sucursal;

public class SucursalRepository {

    private SucursalDao mSucursalDao;
    private LiveData<List<Sucursal>> mAllSucursales;

    public SucursalRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mSucursalDao = db.sucursalDao();
        mAllSucursales = mSucursalDao.getAllSucursales();
    }

    public LiveData<List<Sucursal>> getAllSucursales() {
        return mAllSucursales;
    }

    public LiveData<Sucursal> getSucursal(int id) {
        LiveData<Sucursal> suc = mSucursalDao.getSucursal(id);
        return mSucursalDao.getSucursal(id);
    }

    public void insert (Sucursal sucursal) { new insertAsyncTask(mSucursalDao).execute(sucursal); }

    public void update (Sucursal sucursal) { new updateAsyncTask(mSucursalDao).execute(sucursal); }

    private static class insertAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        insertAsyncTask(SucursalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sucursal... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Sucursal, Void, Void> {

        private SucursalDao mAsyncTaskDao;

        updateAsyncTask(SucursalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Sucursal... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
