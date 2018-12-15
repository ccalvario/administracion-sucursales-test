package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.databinding.DataBindingUtil;

import prj.ccalvario.administracionsucursales.model.Sucursal;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.viewmodel.SucursalViewModel;
import prj.ccalvario.administracionsucursales.databinding.ActivityAddSucursalBinding;

public class AddSucursalActivity extends AppCompatActivity {

    private SucursalViewModel mSucursalViewModel;
    ActivityAddSucursalBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_sucursal);
        mSucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);


        Bundle b = getIntent().getExtras();
        if(b != null){
            setTitle(getResources().getString(R.string.activity_title_edit_sucursal));
            int id = b.getInt("id");
            mSucursalViewModel.getSucursal(id).observe(this, new Observer<Sucursal>() {
                @Override
                public void onChanged(@Nullable final Sucursal sucursal) {
                    mSucursalViewModel.sucursal.set(sucursal);
                }
            });
        } else {
            mSucursalViewModel.sucursal.set(new Sucursal());
        }



        mBinding.setViewModel(mSucursalViewModel);
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
                if(mSucursalViewModel.ValidateInput()) {
                    mSucursalViewModel.saveSucursal();
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
