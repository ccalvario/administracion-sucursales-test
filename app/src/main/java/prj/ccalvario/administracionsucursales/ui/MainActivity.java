package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import prj.ccalvario.administracionsucursales.utils.SessionManager;
import prj.ccalvario.administracionsucursales.viewmodel.LoginViewModel;
import trikita.log.Log;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        SessionManager.getInstance().setContext(this);
        String email = SessionManager.getInstance().getStoredUsername();
        String password = SessionManager.getInstance().getStoredUPassword();

        if(email == null || email.isEmpty() || password == null || password.isEmpty()) {
            Intent activityIntent = new Intent(this, LoginActivity.class);
            startActivity(activityIntent);
            finish();
            return;
        }

        mLoginViewModel.login(email, password).observe(this, usuario -> {
            mLoginViewModel.setUsuario(usuario);
        });

        mLoginViewModel.getIsLoggedIn().observe(this, response -> {
            if(response) {
                Intent activityIntent = new Intent(this, AdministracionActivity.class);
                startActivity(activityIntent);

            } else {
                Intent activityIntent = new Intent(this, LoginActivity.class);
                startActivity(activityIntent);
            }
            finish();
        });
    }
}
