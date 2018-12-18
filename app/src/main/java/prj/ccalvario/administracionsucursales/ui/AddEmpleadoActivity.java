package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.viewmodel.EmpleadoViewModel;
import prj.ccalvario.administracionsucursales.databinding.ActivityAddEmpleadoBinding;

public class AddEmpleadoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EmpleadoViewModel mEmpleadolViewModel;
    ActivityAddEmpleadoBinding mBinding;
    private List<Sucursal> mSucursales;
    private int mSucursalIdInicial;
    private int mPosicionInicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_empleado);
        mEmpleadolViewModel = ViewModelProviders.of(this).get(EmpleadoViewModel.class);

        mBinding.setViewModel(mEmpleadolViewModel);

        mSucursalIdInicial = 0;
        mPosicionInicial = 0;

        Bundle b = getIntent().getExtras();
        if(b != null){
            int id = b.getInt("id");
            if(id > 0) {
                setTitle(getResources().getString(R.string.activity_title_edit_empleado));

                mEmpleadolViewModel.getEmpleado(id).observe(this, new Observer<Empleado>() {
                    @Override
                    public void onChanged(@Nullable final Empleado empleado) {
                        mEmpleadolViewModel.nombre.set(empleado.getNombre());
                        mEmpleadolViewModel.rfc.set(empleado.getRfc());
                        mEmpleadolViewModel.sucursalId.set(String.valueOf(empleado.getSucursalId()));
                        mEmpleadolViewModel.puesto.set(empleado.getPuesto());
                        mEmpleadolViewModel.setId(empleado.getId());
                    }
                });
            }
            mSucursalIdInicial = b.getInt("sucursalId");
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner_sucursales);
        spinner.setOnItemSelectedListener(this);

        mEmpleadolViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(@Nullable final List<Sucursal> sucursales) {
                mSucursales = sucursales;
                List<String> list = new ArrayList<String>();
                for(int i = 0; i < sucursales.size(); i++) {
                    list.add(sucursales.get(i).getNombre());
                    if(mSucursalIdInicial > 0 && sucursales.get(i).getId() == mSucursalIdInicial) {
                        mPosicionInicial = i;
                    }
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddEmpleadoActivity.this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                Log.d("ccz mSucursalIdInicial "+ mSucursalIdInicial + " mPosicionInicial " + mPosicionInicial);
                if(mSucursalIdInicial > 0) {
                    spinner.setSelection(mPosicionInicial);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_sucursal_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if(mEmpleadolViewModel.ValidateInput()) {
                    mEmpleadolViewModel.saveEmpleado();
                    finish();
                }
                return true;
/*            case R.id.action_delete:
                return true;*/

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int sucursalId = mSucursales.get(position).getId();
        mEmpleadolViewModel.sucursalId.set(String.valueOf(sucursalId));
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
