package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import prj.ccalvario.administracionsucursales.adapter.CustomItemClickListener;
import prj.ccalvario.administracionsucursales.utils.SessionManager;
import prj.ccalvario.administracionsucursales.model.SucursalEmpleados;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.viewmodel.SucursalViewModel;
import prj.ccalvario.administracionsucursales.adapter.SucursalListAdapter;

public class AdministracionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SucursalViewModel mSucursalViewModel;
    private List<SucursalEmpleados> mEmpleadosSucursal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        TextView navUsusario = (TextView)hView.findViewById(R.id.textView_navHeader_nombre);
        navUsusario.setText(SessionManager.getInstance().getUsuario().getNombre());

        TextView navEmpresa = (TextView)hView.findViewById(R.id.textView_navHeader_empresa);
        navEmpresa.setText(SessionManager.getInstance().getUsuario().getEmpresa());

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sucursalList);
        final SucursalListAdapter adapter = new SucursalListAdapter(this, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Sucursal sucursal = mEmpleadosSucursal.get(position).sucursal;
                Intent intent = new Intent(AdministracionActivity.this, DetalleSucursalActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", sucursal.getId());
                intent.putExtras(b);
                startActivity(intent);
            }

            @Override
            public void onEditClick(View v, int position) {
                Sucursal sucursal = mEmpleadosSucursal.get(position).sucursal;
                if(sucursal != null) {
                    Intent intent = new Intent(AdministracionActivity.this, AddSucursalActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", sucursal.getId());
                    intent.putExtras(b);
                    startActivity(intent);
                }

            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mSucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        mSucursalViewModel.getSucursalesConEmpleados().observe(this, new Observer<List<SucursalEmpleados>>() {
            @Override
            public void onChanged(@Nullable final List<SucursalEmpleados> sucursales) {

                adapter.setSucursales(sucursales);
                mEmpleadosSucursal = sucursales;

/*                for(int i = 0; i < sucursales.size(); i++) {
                    Log.d("Lista de sucursales "
                            + sucursales.get(i).sucursal.getId()
                            + " nombre " + sucursales.get(i).sucursal.getNombre()
                            + " numEmp " + sucursales.get(i).empleados.size()
                            + " colonia " + sucursales.get(i).sucursal.getColonia()
                            + " numero " + sucursales.get(i).sucursal.getNumero()
                            + " codigoPostal " + sucursales.get(i).sucursal.getCodigoPostal());
                }*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_administracion) {
        } else if (id == R.id.nav_add_sucursal) {
            Intent activityIntent = new Intent(AdministracionActivity.this, AddSucursalActivity.class);
            startActivity(activityIntent);

        } else if (id == R.id.nav_add_empleado) {
            Intent activityIntent = new Intent(AdministracionActivity.this, AddEmpleadoActivity.class);
            startActivity(activityIntent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(AdministracionActivity.this, LoginActivity.class);
            SessionManager.getInstance().logOut();
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
