package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;

import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.utils.SessionManager;
import prj.ccalvario.administracionsucursales.model.Usuario;
import prj.ccalvario.administracionsucursales.repository.UsuarioRepository;

public class LoginViewModel extends AndroidViewModel {

    private UsuarioRepository mUsuarioRepository;

    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();
    public final ObservableField<String> errorLogin = new ObservableField<>();

    private final LiveData<Boolean> isLoggedIn;
    private final MutableLiveData<Usuario> usuario;

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    public LoginViewModel(Application application) {
        super(application);
        mUsuarioRepository = new UsuarioRepository(application);

        usuario = new MutableLiveData<>();
        isLoggedIn = Transformations.map(usuario, user -> {
            if(user != null && user.getId() > 0) {
                SessionManager.getInstance().setUsuario(usuario.getValue());
                if(password.get() != null && !password.get().isEmpty()) {
                    SessionManager.getInstance().storeCredencials(email.get(), password.get());
                }

                errorLogin.set(null);
                return true;
            } else {
                if(email.get() != null && !email.get().isEmpty() && password.get() != null && !password.get().isEmpty()) {
                    errorLogin.set(getApplication().getResources().getString(R.string.error_login));
                } else {
                    errorLogin.set(null);
                }
                return false;
            }
        });

    }

    public LiveData<Boolean> isLoggedIn() { return isLoggedIn; }

    public LiveData<Usuario> getUsuario(int id) { return mUsuarioRepository.getUsuario(id); }

    public LiveData<Usuario> getUsuarioByEmailPassword(String email, String password) { return mUsuarioRepository.getUsuarioByEmailPassword(email, password); }


    public LiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setUsuario(Usuario usuario) { this.usuario.setValue(usuario); }

    public LiveData<Usuario> login(String email, String password) {
        return getUsuarioByEmailPassword(email, password);
    }

    public LiveData<Usuario> startLogin() {
        if (validateInput()) {
            return login(email.get(), password.get());
        }
        return ABSENT;
    }


    public boolean validateInput() {
        boolean result = true;
        if(email.get() == null || email.get().isEmpty()) {
            errorEmail.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorEmail.set(null);
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
