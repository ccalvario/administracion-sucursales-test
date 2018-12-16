package prj.ccalvario.administracionsucursales.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class SucursalEmpleados {

    @Embedded
    public Sucursal sucursal;

    @Relation(parentColumn = "id", entityColumn = "sucursalId", entity = Empleado.class)
    public List<Empleado> empleados;
}
