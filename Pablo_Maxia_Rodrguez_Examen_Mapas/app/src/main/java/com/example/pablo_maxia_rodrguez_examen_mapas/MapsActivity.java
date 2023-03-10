package com.example.pablo_maxia_rodrguez_examen_mapas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.pablo_maxia_rodrguez_examen_mapas.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final ArrayList<LatLng> posiciones = new ArrayList<>();
    private LatLng ultima = null;
    private LatLng previa = null;
    private Location location = null;
    private LocationManager locationManager;
    private MarkerOptions markerOptions;
    private String provider;
    private double distancia, distanciaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolbar;
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.option1:
                        colocarPosicionLatLng();
                        break;
                    case R.id.option2:
                        colocarPosicionDistRumbo();
                        break;
                    case R.id.option3:
                        mostrarUbicacion();
                        break;
                    case R.id.option4:
                        borrarPosiciones();
                        break;
                }
                return true;
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        obtenerPermisos();

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        ultima = (new LatLng(0, 0));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ultima));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void colocarPosicionLatLng() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View viewInflated = getLayoutInflater().inflate(R.layout.latlng_dialog, null);
        EditText latText = viewInflated.findViewById(R.id.latitud);
        EditText longText = viewInflated.findViewById(R.id.longitud);
        alert.setTitle("Colocar posici??n");
        alert.setMessage("Latitud y longitud");
        alert.setView(viewInflated);
        alert.setPositiveButton("S??", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                double lat = Double.parseDouble(latText.getText().toString());
                double lng = Double.parseDouble(longText.getText().toString());
                ultima = new LatLng(lat, lng);

                anadirMarcadorMapa(ultima, "Posici??n actual");
                posiciones.add(ultima);
                previa = ultima;
                anadirPosicionesLinea();

                dialog.dismiss();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alert.show();
    }

    private void colocarPosicionDistRumbo() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View viewInflated = getLayoutInflater().inflate(R.layout.dist_dialog, (ViewGroup) binding.getRoot(), false);
        EditText distText = viewInflated.findViewById(R.id.distancia);
        EditText rumboText = viewInflated.findViewById(R.id.rumbo);
        alert.setTitle("Colocar posici??n");
        alert.setMessage("Distancia (KM) y rumbo (??)");
        alert.setView(viewInflated);
        alert.setPositiveButton("S??", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                double dist = Double.parseDouble(distText.getText().toString());
                double rumbo = Double.parseDouble(rumboText.getText().toString());
                dist *= 1000;
                if (rumbo < 0) rumbo += 360;
                LatLng destino = SphericalUtil.computeOffset(ultima, dist, rumbo);
                ultima = destino;

                if (ultima != null) {
                    posiciones.add(ultima);
                    previa = ultima;
                    anadirMarcadorMapa(ultima, "Posici??n actual");
                    dialog.dismiss();

                    anadirPosicionesLinea();
                }
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alert.show();
    }


    @SuppressLint("MissingPermission")
    private void mostrarUbicacion() {
        //Inicializar el manager que nos va a dar la geoposici??n en base al GPS
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Se usa la clase Criteria para obtener el mejor proveedor de localizaci??n
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);

        //false se establece para que no est?? activo permanentemente
        provider = locationManager.getBestProvider(criteria, false);

        obtenerPermisos();
        mMap.setMyLocationEnabled(true);

        if (locationManager.isProviderEnabled(provider)) {
            this.location = locationManager.getLastKnownLocation(provider);
            if (this.location == null) {
                this.location = new Location(provider);
            }
            onLocationChanged(location);
            ultima = new LatLng(location.getLatitude(), location.getLongitude());
            posiciones.add(ultima);
            anadirMarcadorMapa(ultima, "Posici??n actual");
            anadirPosicionesLinea();
        }

        //Obtenemos la primera localizaci??n que nos sirve de referencia
        Log.d(":::MAPA", location + "");
    }

    private void borrarPosiciones() {
        mMap.clear();
        posiciones.clear();
    }

    private void anadirMarcadorMapa(LatLng posicion, String texto) {
        markerOptions = new MarkerOptions().position(posicion).title(texto);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));
    }

    private void anadirPosicionesLinea() {
        PolylineOptions polylineOptions = new PolylineOptions().addAll(posiciones).color(Color.RED);
        mMap.addPolyline(polylineOptions);
    }

    private void obtenerPermisos() {
        // Se verifica si la aplicaci??n tiene los permisos para acceder
        // a la ubicaci??n del dispositivo (ACCESS_FINE_LOCATION y ACCESS_COARSE_LOCATION).
        // Si no tiene permisos, se solicita al usuario que los permita mediante
        // la funci??n requestPermissions()
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000
            );
        }
        return;
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        this.location = location;

        //Para calcular distancias entre dos puntos en metros
        //float distancia=location.distanceTo(referencia);
        //Log.d("/posicionamiento"," "+distancia);

        //Paquete de Android para resolver coordenadas a partir de una direcci??n y
        //viceversa
    }
}