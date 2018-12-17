package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import prj.ccalvario.administracionsucursales.db.SessionManager;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.viewmodel.LoginViewModel;
import prj.ccalvario.administracionsucursales.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mBinding.setViewModel(mLoginViewModel);

        mBinding.btnLogin.setOnClickListener(
                (View view) -> {
                    Log.d("LoginActivity", "ccz startLogin");
                    Log.d("LoginActivity", "ccz mViewModel " + mLoginViewModel.email.get());
                    mLoginViewModel.startLogin().observe(this, usuario -> {

                        mLoginViewModel.setUsuario(usuario);
                        if(usuario != null && usuario.getId() > 0) {
                            Log.d("ccz Login success "+ usuario.getId());
                        } else {
                            Log.d("ccz invalid user");
                        }

                    });
                });

        mBinding.btnCrear.setOnClickListener(
                (View view) -> {
                    Intent intent = new Intent(LoginActivity.this, AddUsuarioActivity.class);
                    startActivity(intent);
                    //finish();
                });

        mLoginViewModel.getIsLoggedIn().observe(this, response -> {
            if(response) {
                Intent intent = new Intent(LoginActivity.this, AdministracionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
