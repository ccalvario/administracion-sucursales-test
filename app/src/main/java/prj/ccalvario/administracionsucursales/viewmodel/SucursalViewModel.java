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
    private int mId;

    //public final ObservableField<Sucursal> sucursal = new ObservableField<>();
    public final ObservableField<SucursalEmpleados> sucursalEmpleados = new ObservableField<>();
    public final ObservableField<List<Empleado>> listaEmpleadosSucursal = new ObservableField<>();
    public final ObservableField<String> nombre = new ObservableField<>();
    public final ObservableField<String> calle = new ObservableField<>();
    public final ObservableField<String> colonia = new ObservableField<>();
    public final ObservableField<String> numero = new ObservableField<>();
    public final ObservableField<String> codigoPostal = new ObservableField<>();
    public final ObservableField<String> ciudad = new ObservableField<>();
    public final ObservableField<String> pais = new ObservableField<>();

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

    public void setId(int id) { mId = id; }

    public int getId() { return mId; }

    public LiveData<List<Sucursal>> getAllSucursales() { return mSucursalRepository.getAllSucursales(); }

    public LiveData<List<SucursalEmpleados>> getSucursalesConEmpleados() { return mSucursalRepository.getSucursalesConEmpleados(); }

    public void insert(Sucursal sucursal) { mSucursalRepository.insert(sucursal); }

    public void update(Sucursal sucursal) { mSucursalRepository.update(sucursal); }

    public LiveData<Sucursal> getSucursal(int id) { return mSucursalRepository.getSucursal(id); }

    public LiveData<SucursalEmpleados> getSucursalConEmpleados(int id) { return mSucursalRepository.getSucursalConEmpleados(id); }

    public void saveSucursal() {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre.get());
        sucursal.setCalle(calle.get());
        sucursal.setColonia(colonia.get());
        sucursal.setNumero(numero.get());
        sucursal.setCodigoPostal(codigoPostal.get());
        sucursal.setCiudad(ciudad.get());
        sucursal.setPais(pais.get());

        if(mId > 0){
            sucursal.setId(mId);
            update(sucursal);
        } else {
            insert(sucursal);
        }
    }

    public boolean ValidateInput() {
        boolean result = true;

        if(nombre.get() == null) {
            errorNombre.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNombre.set(null);
        }

        if(calle.get() == null) {
            errorCalle.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCalle.set(null);
        }

        if(colonia.get() == null) {
            errorColonia.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorColonia.set(null);
        }

        if(numero.get() == null) {
            errorNumero.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorNumero.set(null);
        }

        if(codigoPostal.get() == null) {
            errorCodigoPostal.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCodigoPostal.set(null);
        }

        if(ciudad.get() == null) {
            errorCiudad.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorCiudad.set(null);
        }

        if(pais.get() == null) {
            errorPais.set(getApplication().getResources().getString(R.string.error_campo_obligatorio));
            result = false;
        } else {
            errorPais.set(null);
        }
        return result;
    }
}
