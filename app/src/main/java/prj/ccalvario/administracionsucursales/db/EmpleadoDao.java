package prj.ccalvario.administracionsucursales.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Empleado;

@Dao
public interface EmpleadoDao {

    @Query("SELECT * FROM empleados")
    LiveData<List<Empleado>> getAllEmpleados();

    @Query("SELECT * FROM empleados WHERE sucursalId = :sucursalId")
    LiveData<List<Empleado>> getEmpleadosSucursal(int sucursalId);

    @Query("SELECT * FROM empleados WHERE id = :id")
    LiveData<Empleado> getEmpleado(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Empleado empleado);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Empleado empleado);

    @Query("DELETE FROM empleados")
    void deleteAll();
}
