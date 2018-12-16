package prj.ccalvario.administracionsucursales.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "empleados",
        foreignKeys = @ForeignKey(entity = Sucursal.class,
        parentColumns = "id",
        childColumns = "sucursalId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = "sucursalId")
        })

public class Empleado {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    String nombre;

    @NonNull
    String rfc;

    @NonNull
    int sucursalId;

    @NonNull
    String puesto;

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getRfc() { return rfc; }

    public int getSucursalId() { return sucursalId; }

    public String getPuesto() { return puesto; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setRfc(String rfc) { this.rfc = rfc; }

    public void setSucursalId(int id) { this.sucursalId = id; }

    public void setPuesto(String puesto) { this.puesto = puesto; }
}
