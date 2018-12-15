package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import prj.ccalvario.administracionsucursales.adapter.CustomItemClickListener;
import trikita.log.Log;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.model.Sucursal;
import prj.ccalvario.administracionsucursales.viewmodel.SucursalViewModel;
import prj.ccalvario.administracionsucursales.adapter.SucursalListAdapter;

public class AdministracionActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private SucursalViewModel mSucursalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch(menuItem.getItemId()) {
                            case R.id.nav_administracion:
                                break;
                            case R.id.nav_add_sucursal:
                                Intent activityIntent = new Intent(AdministracionActivity.this, AddSucursalActivity.class);
                                startActivity(activityIntent);
                                break;
                            case R.id.nav_add_empleado:
                                break;
                            case R.id.nav_logout:
                                break;

                        }

                        return true;
                    }
                });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sucursalList);
        final SucursalListAdapter adapter = new SucursalListAdapter(this, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String nombre = mSucursalViewModel.getAllSucursales().getValue().get(position).getNombre();
                Log.d("ccz onItemClick " + nombre);
                /*Intent intent = new Intent(AdministracionActivity.this, AddSucursalActivity.class);
                Bundle b = new Bundle();
                b.putInt("position", position);
                intent.putExtras(b);
                startActivityForResult(intent, 1);*/
            }

            @Override
            public void onEditClick(View v, int position) {
                Log.d("ccz onItemClick " + position);
                Sucursal sucursal = mSucursalViewModel.getAllSucursales().getValue().get(position);
                if(sucursal != null) {
                    Intent intent = new Intent(AdministracionActivity.this, AddSucursalActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("id", sucursal.getId());
                    intent.putExtras(b);
/*                b.putString("calle", sucursal.getCalle());
                b.putString("colonia", sucursal.getColonia());
                b.putString("numero", Integer.toString(sucursal.getNumero()));
                b.putString("codigoPostal", Integer.toString(sucursal.getCodigoPostal()));
                b.putString("ciudad", sucursal.getNombre());
                b.putString("pais", sucursal.getNombre());*/
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

        mSucursalViewModel.getAllSucursales().observe(this, new Observer<List<Sucursal>>() {
            @Override
            public void onChanged(@Nullable final List<Sucursal> sucursales) {

                adapter.setSucursales(sucursales);

                for(int i = 0; i < sucursales.size(); i++) {
                    Log.d("ccz Lista de sucursales "
                            + sucursales.get(i).getId()
                            + " nombre " + sucursales.get(i).getNombre()
                            + " colonia " + sucursales.get(i).getColonia()
                            + " numero " + sucursales.get(i).getNumero()
                            + " codigoPostal " + sucursales.get(i).getCodigoPostal());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
