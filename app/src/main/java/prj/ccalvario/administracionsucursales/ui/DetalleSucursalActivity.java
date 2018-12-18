package prj.ccalvario.administracionsucursales.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import prj.ccalvario.administracionsucursales.R;
import prj.ccalvario.administracionsucursales.databinding.ActivityDetalleSucursalBinding;
import prj.ccalvario.administracionsucursales.model.SucursalEmpleados;
import prj.ccalvario.administracionsucursales.viewmodel.SucursalViewModel;
import trikita.log.Log;

public class DetalleSucursalActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private SucursalViewModel mSucursalViewModel;
    ActivityDetalleSucursalBinding mBinding;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_sucursal);
        mSucursalViewModel = ViewModelProviders.of(this).get(SucursalViewModel.class);

        mBinding.setViewModel(mSucursalViewModel);

        Bundle b = getIntent().getExtras();
        if(b != null){
            int id = b.getInt("id");
            mSucursalViewModel.getSucursalConEmpleados(id).observe(this, new Observer<SucursalEmpleados>() {
                @Override
                public void onChanged(@Nullable final SucursalEmpleados sucursalEmpleados) {

                    mSucursalViewModel.sucursalEmpleados.set(sucursalEmpleados);
                    Log.d("ccz sucursalEmpleados " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getNombre());

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(DetalleSucursalActivity.this);
                }
            });
        } else {
            mSucursalViewModel.sucursalEmpleados.set(new SucursalEmpleados());
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(mSucursalViewModel.sucursalEmpleados.get() != null) {
            Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
            try {
                String address = mSucursalViewModel.sucursalEmpleados.get().sucursal.getCalle() +
                        " " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getNumero() +
                        " " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getColonia() +
                        " C.P. " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getCodigoPostal() +
                        " " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getCiudad() +
                        " " + mSucursalViewModel.sucursalEmpleados.get().sucursal.getPais();

                Log.d("ccz address " + address);
                List<Address> addresses = geoCoder.getFromLocationName(address, 5);
                if (addresses.size() > 0) {
                    Double lat = (double) (addresses.get(0).getLatitude());
                    Double lon = (double) (addresses.get(0).getLongitude());

                    LatLng mark = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(mark).title(mSucursalViewModel.sucursalEmpleados.get().sucursal.getNombre()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mark, 12));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.showInfoWindow();
        return true;
    }
}
