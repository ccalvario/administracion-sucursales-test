package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.databinding.ActivityDetalleSucursalBinding;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.model.SucursalEmpleados;
import prj.ccalvario.administracionsucursales.viewmodel.SucursalViewModel;

public class DetalleSucursalActivity extends AppCompatActivity {

    private SucursalViewModel mSucursalViewModel;
    ActivityDetalleSucursalBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_sucursal);
        mSucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        mBinding.setViewModel(mSucursalViewModel);

        Bundle b = getIntent().getExtras();
        if(b != null){
            int id = b.getInt("id");
            mSucursalViewModel.getSucursalEmpleados(id).observe(this, new Observer<SucursalEmpleados>() {
                @Override
                public void onChanged(@Nullable final SucursalEmpleados sucursalEmpleados) {
                    mSucursalViewModel.sucursalEmpleados.set(sucursalEmpleados);
                }
            });
        } else {
            mSucursalViewModel.sucursalEmpleados.set(new SucursalEmpleados());
        }
    }
}
