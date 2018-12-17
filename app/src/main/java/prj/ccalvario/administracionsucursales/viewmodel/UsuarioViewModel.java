package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;

import prj.ccalvario.administracionsucursales.model.Usuario;
import prj.ccalvario.administracionsucursales.repository.UsuarioRepository;

public class UsuarioViewModel extends AndroidViewModel {

    private UsuarioRepository mUsuarioRepository;

    public final ObservableField<Usuario> usuario = new ObservableField<>();

    public final ObservableField<String> errorEmail = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();

    public UsuarioViewModel(Application application) {
        super(application);
        mUsuarioRepository = new UsuarioRepository(application);
    }

    public LiveData<Usuario> getUsuario(int id) { return mUsuarioRepository.getUsuario(id); }
}
