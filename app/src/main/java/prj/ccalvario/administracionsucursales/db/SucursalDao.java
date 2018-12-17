package prj.ccalvario.administracionsucursales.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.model.SucursalEmpleados;

@Dao
public interface SucursalDao {

    @Query("SELECT * FROM sucursales")
    LiveData<List<Sucursal>> getAllSucursales();

    @Query("SELECT * FROM sucursales")
    @Transaction
    public LiveData<List<SucursalEmpleados>> getSucursalesEmpleados();

    @Query("SELECT * FROM sucursales WHERE id = :id")
    LiveData<Sucursal> getSucursal(int id);

    @Query("SELECT * FROM sucursales WHERE id = :id")
    @Transaction
    LiveData<SucursalEmpleados> getSucursalEmpleados(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sucursal sucursal);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Sucursal sucursal);

    @Query("DELETE FROM sucursales")
    void deleteAll();
}
