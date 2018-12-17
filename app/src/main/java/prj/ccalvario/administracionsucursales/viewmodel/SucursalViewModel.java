package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.model.SucursalEmpleados;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.repository.SucursalRepository;

public class SucursalViewModel extends AndroidViewModel {

    private SucursalRepository mSucursalRepository;

    public final ObservableField<Sucursal> sucursal = new ObservableField<>();
    public final ObservableField<SucursalEmpleados> sucursalEmpleados = new ObservableField<>();
    public final ObservableField<List<Empleado>> empleadosSucursal = new ObservableField<>();

    public final ObservableField<String> errorNombre = new ObservableField<>();
    public final ObservableField<String> errorCalle = new ObservableField<>();
    public final ObservableField<String> errorColonia = new ObservableField<>();
    public final ObservableField<String> errorNumero = new ObservableField<>();
    public final ObservableField<String> errorCodigoPostal = new ObservableField<>();
    public final ObservableField<String> errorCiudad = new ObservableField<>();
    public final ObservableField<String> errorPais = new ObservableField<>();

    public SucursalViewModel(Application application) {
        super(application);
        mSucursalRepository = new SucursalRepository(application);
    }

    public LiveData<List<Sucursal>> getAllSucursales(String usuarioId) { return mSucursalRepository.getAllSucursales(usuarioId); }

    public LiveData<List<SucursalEmpleados>> getSucursalesConEmpleados(String usuarioId) { return mSucursalRepository.getSucursalesConEmpleados(usuarioId); }

    public void insert(Sucursal sucursal) { mSucursalRepository.insert(sucursal); }

    public void update(Sucursal sucursal) { mSucursalRepository.update(sucursal); }

    public LiveData<Sucursal> getSucursal(int id) { return mSucursalRepository.getSucursal(id); }

    public LiveData<SucursalEmpleados> getSucursalConEmpleados(int id) { return mSucursalRepository.getSucursalConEmpleados(id); }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal.set(sucursal);
    }

    public void saveSucursal() {
        if(sucursal.get().getId() > 0){
            update(sucursal.get());
        } else {
            insert(sucursal.get());
        }
    }

    public boolean ValidateInput() {
        boolean result = true;

        if(sucursal.get().getNombre() == null) {
            errorNombre.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNombre.set(null);
        }

        if(sucursal.get().getCalle() == null) {
            errorCalle.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCalle.set(null);
        }

        if(sucursal.get().getColonia() == null) {
            errorColonia.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorColonia.set(null);
        }

        if(sucursal.get().getNumero() == null) {
            errorNumero.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNumero.set(null);
        }

        if(sucursal.get().getCodigoPostal() == null) {
            errorCodigoPostal.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCodigoPostal.set(null);
        }

        if(sucursal.get().getCiudad() == null) {
            errorCiudad.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCiudad.set(null);
        }

        if(sucursal.get().getPais() == null) {
            errorPais.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorPais.set(null);
        }
        return result;
    }
}
