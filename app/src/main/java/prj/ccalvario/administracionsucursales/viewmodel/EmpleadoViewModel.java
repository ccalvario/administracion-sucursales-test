package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Sucursal;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.repository.EmpleadoRepository;
import prj.ccalvario.administracionsucursales.repository.SucursalRepository;

public class EmpleadoViewModel extends AndroidViewModel {

    private EmpleadoRepository mEmpleadoRepository;
    private SucursalRepository mSucursalRepositoy;
    //private LiveData<List<Empleado>> mAllSucursales;

    public final ObservableField<Empleado> empleado = new ObservableField<>();

    public final ObservableField<String> errorNombre = new ObservableField<>();
    public final ObservableField<String> errorRfc = new ObservableField<>();
    public final ObservableField<String> errorSucursalId = new ObservableField<>();
    public final ObservableField<String> errorPuesto = new ObservableField<>();

    public EmpleadoViewModel(Application application) {
        super(application);
        mEmpleadoRepository = new EmpleadoRepository(application);
        mSucursalRepositoy = new SucursalRepository(application);
    }

    public LiveData<Empleado> getEmpleado(int id) { return mEmpleadoRepository.getEmpleado(id); }

    public LiveData<List<Empleado>> getEmpleadosSucursal(int sucursalId) { return mEmpleadoRepository.getEmpleadosSucursales(sucursalId); }

    public LiveData<List<Sucursal>> getAllSucursales() { return mSucursalRepositoy.getAllSucursales(); }

    public void insert(Empleado empleado) { mEmpleadoRepository.insert(empleado); }

    public void update(Empleado empleado) { mEmpleadoRepository.update(empleado); }

    public void saveEmpleado() {
        if(empleado.get().getId() > 0){
            update(empleado.get());
        } else {
            insert(empleado.get());
        }
    }

    public boolean ValidateInput() {
        boolean result = true;

        if(empleado.get().getNombre() == null) {
            errorNombre.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNombre.set(null);
        }

        return result;
    }
}
