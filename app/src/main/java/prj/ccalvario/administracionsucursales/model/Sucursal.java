package prj.ccalvario.administracionsucursales.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "sucursales")
public class Sucursal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nombre;

    @NonNull
    private String calle;

    @NonNull
    private String colonia;

    @NonNull
    private String numero;

    @NonNull
    private String codigoPostal;

    @NonNull
    private String ciudad;

    @NonNull
    private String pais;

    //@NonNull
    private String usuario;

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getCalle() { return calle; }

    public String getColonia() { return colonia; }

    public String getNumero() { return numero; }

    public String getCodigoPostal() { return codigoPostal; }

    public String getCiudad() { return ciudad; }

    public String getPais() { return pais; }

    public String getUsuario() { return usuario; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setCalle(String calle) { this.calle = calle; }

    public void setColonia(String colonia) { this.colonia = colonia; }

    public void setNumero(String numero) { this.numero = numero; }

    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public void setPais(String pais) { this.pais = pais; }

    public void setUsuario(String usuario) { this.usuario = usuario; }
}
