package prj.ccalvario.administracionsucursales.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.db.AppDatabase;
import prj.ccalvario.administracionsucursales.db.EmpleadoDao;
import prj.ccalvario.administracionsucursales.model.Empleado;

public class EmpleadoRepository {

    private EmpleadoDao mEmpleadoDao;

    public EmpleadoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mEmpleadoDao = db.empleadoDao();
    }

    public LiveData<Empleado> getEmpleado(int id) {
        return mEmpleadoDao.getEmpleado(id);
    }

    public LiveData<List<Empleado>> getEmpleadosSucursales(int sucursalId) {
        return mEmpleadoDao.getEmpleadosSucursal(sucursalId);
    }

    public void insert (Empleado empleado) { new insertAsyncTask(mEmpleadoDao).execute(empleado); }

    public void update (Empleado empleado) { new updateAsyncTask(mEmpleadoDao).execute(empleado); }

    private static class insertAsyncTask extends AsyncTask<Empleado, Void, Void> {

        private EmpleadoDao mAsyncTaskDao;

        insertAsyncTask(EmpleadoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Empleado... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Empleado, Void, Void> {

        private EmpleadoDao mAsyncTaskDao;

        updateAsyncTask(EmpleadoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Empleado... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
