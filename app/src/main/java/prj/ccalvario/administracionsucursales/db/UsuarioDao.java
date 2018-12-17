package prj.ccalvario.administracionsucursales.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Usuario;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM usuarios")
    LiveData<List<Usuario>> getAllUsuarios();

    @Query("SELECT * FROM usuarios WHERE id = :id")
    LiveData<Usuario> getUsuario(int id);

    @Query("SELECT * FROM usuarios WHERE email = :email")
    LiveData<Usuario> getUsuario(String email);

    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :password")
    LiveData<Usuario> getUsuarioByEmailPassword(String email, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Usuario usuario);

    @Query("DELETE FROM usuarios")
    void deleteAll();
}
