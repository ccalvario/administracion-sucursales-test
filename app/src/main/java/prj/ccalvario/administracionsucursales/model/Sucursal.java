package prj.ccalvario.administracionsucursales.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Sucursal {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    private String mNombre;

    private String mCalle;

    private String mColonia;

    private String mNumero;

    private String mCodigoPostal;

    private String mCiudad;

    private String mPais;

    private String mUsuario;

    public int getId() { return mId; }

    public String getNombre() { return mNombre; }

    public String getCalle() { return mCalle; }

    public String getColonia() { return mColonia; }

    public String getNumero() { return mNumero; }

    public String getCodigoPostal() { return mCodigoPostal; }

    public String getCiudad() { return mCiudad; }

    public String getPais() { return mPais; }

    public String getUsuario() { return mUsuario; }

    public void setId(int id) { mId = id; }

    public void setNombre(String nombre) { mNombre = nombre; }

    public void setCalle(String calle) { mCalle = calle; }

    public void setColonia(String colonia) { mColonia = colonia; }

    public void setNumero(String numero) { mNumero = numero; }

    public void setCodigoPostal(String codigoPostal) { mCodigoPostal = codigoPostal; }

    public void setCiudad(String ciudad) { mCiudad = ciudad; }

    public void setPais(String pais) { mPais = pais; }

    public void setUsuario(String usuario) { mUsuario = usuario; }
}
