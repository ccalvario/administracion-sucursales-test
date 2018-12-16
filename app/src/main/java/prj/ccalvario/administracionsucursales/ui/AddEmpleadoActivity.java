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

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Empleado;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.viewmodel.EmpleadoViewModel;
import prj.ccalvario.administracionsucursales.databinding.ActivityAddEmpleadoBinding;

public class AddEmpleadoActivity extends AppCompatActivity {

    private EmpleadoViewModel mEmpleadolViewModel;
    ActivityAddEmpleadoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_empleado);
        mEmpleadolViewModel = ViewModelProviders.of(this).get(EmpleadoViewModel.class);

        mBinding.setViewModel(mEmpleadolViewModel);

        Bundle b = getIntent().getExtras();
        if(b != null){
            setTitle(getResources().getString(R.string.activity_title_edit_empleado));
            int id = b.getInt("id");
            mEmpleadolViewModel.getEmpleado(id).observe(this, new Observer<Empleado>() {
                @Override
                public void onChanged(@Nullable final Empleado empleado) {
                    mEmpleadolViewModel.empleado.set(empleado);
                }
            });
        } else {
            mEmpleadolViewModel.empleado.set(new Empleado());
        }
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
}
