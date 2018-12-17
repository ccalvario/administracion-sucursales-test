package prj.ccalvario.administracionsucursales.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.model.Usuario;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.model.Sucursal;

@Database(entities = {Sucursal.class, Empleado.class, Usuario.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "sucursales-db";

    public abstract SucursalDao sucursalDao();
    public abstract EmpleadoDao empleadoDao();
    public abstract UsuarioDao usuarioDao();

    public static AppDatabase getInstance(final Context context) {
        if(sInstance == null) {
            synchronized (AppDatabase.class) {
                sInstance =  Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration() //destroy and re-create the database
                        //.addCallback(sAppDatabaseCallback) //populate database
                        .build();
            }
        }
        return sInstance;
    }

    private static AppDatabase.Callback sAppDatabaseCallback =
            new AppDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final SucursalDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.sucursalDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            //mDao.deleteAll();
            Log.d("Populate Database");

            for (int i = 0; i < 3 - 1; i++) {
                Sucursal sucursal = new Sucursal();
                //sucursal.setId(i);
                sucursal.setNombre("Sucursal " + i);
                mDao.insert(sucursal);
            }
            return null;
        }
    }
}
