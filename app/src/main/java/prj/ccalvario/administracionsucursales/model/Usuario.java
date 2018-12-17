package prj.ccalvario.administracionsucursales.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "usuarios", indices = {@Index(value = "email", unique = true)})
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String nombre;

    @NonNull
    private String email;

    @NonNull
    private String rfc;

    @NonNull
    private String empresa;

    @NonNull
    private String password;

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getEmail() { return email; }

    public String getRfc() { return rfc; }

    public String getEmpresa() { return empresa; }

    public String getPassword() { return password; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setEmail(String email) { this.email = email; }

    public void setRfc(String rfc) { this.rfc = rfc; }

    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public void setPassword(String password) { this.password = password; }
}
