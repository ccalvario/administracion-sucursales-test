package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;;
import android.databinding.ObservableField;

import java.util.List;

import prj.ccalvario.administracionsucursales.R;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.model.Usuario;
import prj.ccalvario.administracionsucursales.repository.UsuarioRepository;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepository mUsuarioRepository;

    public final ObservableField<String> nombre = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> rfc = new ObservableField<>();
    public final ObservableField<String> empresa = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableField<String> errorNombre = new ObservableField<>();
    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorRfc = new ObservableField<>();
    public final ObservableField<String> errorEmpresa = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();

    public UsuarioViewModel(Application application) {
        super(application);
        mUsuarioRepository = new UsuarioRepository(application);
    }

    public LiveData<List<Usuario>> getAllUsuarios() { return mUsuarioRepository.getAllUsuarios(); }

    public LiveData<Usuario> getUsuario(int id) { return mUsuarioRepository.getUsuario(id); }

    public LiveData<Usuario> getUsuarioByEmailPassword(String email, String password) { return mUsuarioRepository.getUsuarioByEmailPassword(email, password); }

    public void insert(Usuario usuario) { mUsuarioRepository.insert(usuario); }

    public void update(Usuario usuario) { mUsuarioRepository.update(usuario); }

    public boolean registrarUsuario() {
        boolean result = false;
        if (validateInput()) {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre.get());
            usuario.setEmail(email.get());
            usuario.setRfc(rfc.get());
            usuario.setEmpresa(empresa.get());
            usuario.setPassword(password.get());
            mUsuarioRepository.insert(usuario);
            result = true;
        }
        Log.d("ccz login");
        return result;
    }

    public boolean validateInput() {
        boolean result = true;

        if(nombre.get() == null || nombre.get().isEmpty()) {
            errorNombre.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNombre.set(null);
        }

        if(email.get() == null || email.get().isEmpty()) {
            errorEmail.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorEmail.set(null);
        }

        if(rfc.get() == null || rfc.get().isEmpty()) {
            errorRfc.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorRfc.set(null);
        }

        if(empresa.get() == null || empresa.get().isEmpty()) {
            errorEmpresa.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorEmpresa.set(null);
        }


        if(password.get() == null || password.get().isEmpty()) {
            errorPassword.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorPassword.set(null);
        }
        return result;
    }
}
