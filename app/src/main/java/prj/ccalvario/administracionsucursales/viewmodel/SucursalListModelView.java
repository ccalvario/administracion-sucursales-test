package prj.ccalvario.administracionsucursales.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.repository.SucursalRepository;

public class SucursalListModelView extends AndroidViewModel {

    private SucursalRepository mSucursalRepository;
    private LiveData<List<Sucursal>> mAllSucursales;

    public SucursalListModelView (Application application) {
        super(application);
        mSucursalRepository = new SucursalRepository(application);
        mAllSucursales = mSucursalRepository.getAllSucursales();
    }

    public LiveData<List<Sucursal>> getAllSucursales() { return mAllSucursales; }

    public void insert(Sucursal sucursal) { mSucursalRepository.insert(sucursal); }
}
