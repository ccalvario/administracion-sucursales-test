package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.databinding.ActivityAddUsuarioBinding;
import prj.ccalvario.administracionsucursales.model.Usuario;
import prj.ccalvario.administracionsucursales.viewmodel.UsuarioViewModel;
import trikita.log.Log;

public class AddUsuarioActivity extends AppCompatActivity {

    ActivityAddUsuarioBinding mBinding;
    private UsuarioViewModel mUsuarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_usuario);
        mUsuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        mBinding.setViewModel(mUsuarioViewModel);

        mBinding.btnCrear.setOnClickListener(
                (View view) -> {
                    if(mUsuarioViewModel.registrarUsuario()) {
                        finish();
                    }
                });

        mBinding.btnLogin.setOnClickListener(
                (View view) -> {
                    finish();
                });

        mUsuarioViewModel.getAllUsuarios().observe(this, new Observer<List<Usuario>>(){
            @Override
            public void onChanged(@Nullable final List<Usuario> usuarios) {
                for(int i = 0; i < usuarios.size(); i++) {
                    Log.d("ccz Lista de usuarios "
                            + usuarios.get(i).getId()
                            + " nombre " + usuarios.get(i).getNombre()
                            + " email " + usuarios.get(i).getEmail());
                }
            }

        });
    }
}
