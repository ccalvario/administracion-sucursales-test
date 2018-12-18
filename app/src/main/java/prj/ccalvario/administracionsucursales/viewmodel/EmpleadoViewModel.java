package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.utils.Utils;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.repository.EmpleadoRepository;
import prj.ccalvario.administracionsucursales.repository.SucursalRepository;

public class EmpleadoViewModel extends AndroidViewModel {

    private EmpleadoRepository mEmpleadoRepository;
    private SucursalRepository mSucursalRepositoy;
    private int mId;

    public final ObservableField<String> nombre = new ObservableField<>();
    public final ObservableField<String> rfc = new ObservableField<>();
    public final ObservableField<String> sucursalId = new ObservableField<>();
    public final ObservableField<String> puesto = new ObservableField<>();

    public final ObservableField<String> errorNombre = new ObservableField<>();
    public final ObservableField<String> errorRfc = new ObservableField<>();
    public final ObservableField<String> errorSucursalId = new ObservableField<>();
    public final ObservableField<String> errorPuesto = new ObservableField<>();

    public EmpleadoViewModel(Application application) {
        super(application);
        mEmpleadoRepository = new EmpleadoRepository(application);
        mSucursalRepositoy = new SucursalRepository(application);
    }

    public int getId() { return mId; }

    public void setId(int id) { mId = id; }

    public LiveData<Empleado> getEmpleado(int id) { return mEmpleadoRepository.getEmpleado(id); }

    public LiveData<List<Empleado>> getEmpleadosSucursal(int sucursalId) { return mEmpleadoRepository.getEmpleadosSucursales(sucursalId); }

    public LiveData<List<Sucursal>> getAllSucursales() { return mSucursalRepositoy.getAllSucursales(); }

    public void insert(Empleado empleado) { mEmpleadoRepository.insert(empleado); }

    public void update(Empleado empleado) { mEmpleadoRepository.update(empleado); }

    public void saveEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre.get());
        empleado.setRfc(rfc.get());
        empleado.setSucursalId(Integer.parseInt(sucursalId.get()));
        empleado.setPuesto(puesto.get());

        if(mId > 0){
            empleado.setId(mId);
            update(empleado);
        } else {
            insert(empleado);
        }
    }

    public boolean ValidateInput() {
        boolean result = true;

        if(nombre.get() == null || nombre.get().isEmpty()) {
            errorNombre.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNombre.set(null);
        }

        if(rfc.get() == null || rfc.get().isEmpty()) {
            errorRfc.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else if(!Utils.isRfcValid(rfc.get())) {
            errorRfc.set(getApplication().getResources().getString(R.string.error_rfc_invalido));
            result = false;
        } else {
            errorRfc.set(null);
        }

        if(sucursalId.get() == null || sucursalId.get().isEmpty()) {
            errorSucursalId.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorSucursalId.set(null);
        }

        if(puesto.get() == null || puesto.get().isEmpty()) {
            errorPuesto.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorPuesto.set(null);
        }

        return result;
    }
}
