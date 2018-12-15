package prj.ccalvario.administracionsucursales.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Sucursal;

@Dao
public interface SucursalDao {

    @Query("SELECT * FROM Sucursal")
    LiveData<List<Sucursal>> getAllSucursales();

    @Query("SELECT * FROM Sucursal WHERE mId = :id")
    LiveData<Sucursal> getSucursal(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sucursal sucursal);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Sucursal sucursal);

    @Query("DELETE FROM Sucursal")
    void deleteAll();
}
