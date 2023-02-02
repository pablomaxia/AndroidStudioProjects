package com.example.probargps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView txtLat, txtLong, txtSrc, txtDir;
    private LocationManager locationManager;
    private String provider;
    private Location referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        //Vinculamos cada objeto a la parte de la UI respectiva...
        txtLat = (TextView) findViewById(R.id.txt_lat);
        txtLong = (TextView) findViewById(R.id.txt_long);
        txtSrc = (TextView) findViewById(R.id.txt_src);
        txtDir = (TextView) findViewById(R.id.txt_dir);

        //Inicializar el manager que nos va a dar la geoposición en base al GPS
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Se usa la clase Criteria para obtener el mejor proveedor de localización
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //false se establece para que no esté activo permanentemente
        provider = locationManager.getBestProvider(criteria, false);

        //Se verifica si la aplicación tiene los permisos para acceder
        // a la ubicación del dispositivo (ACCESS_FINE_LOCATION y ACCESS_COARSE_LOCATION).
        // Si no tiene permisos, se solicita al usuario que los permita mediante
        // la función requestPermissions()
           if (ActivityCompat.checkSelfPermission(this,
                   Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000
            );
        }


        //Obtenemos la primera localización que nos sirve de referencia
        Location location = locationManager.getLastKnownLocation(provider);
        referencia=location;


        if (location != null) {
            txtSrc.setText("Source = " + provider);
            onLocationChanged(location);
        }
        else{
            Log.d(":::GPS", "La localización no se pudo detectar.");
        }
    }


    //
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }



    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
         ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                 }, 1000
            );
        }
        // requestLocationUpdates registra un "listener" para recibir actualizaciones de la ubicación.
        // el intervalo de tiempo para actualizar argumentos (en milisegundos)
        // y la distancia mínima que debe recorrer el usuario para que se genere una actualización (en metros).
        // Finalmente, la actividad actual se pasa como argumento, ya que implementa la interface "LocationListener",
        // para recibir las actualizaciones de ubicación.

        locationManager.requestLocationUpdates(provider, 500 /*milisegundos de update*/, 1 /*metros de recorrido del usuario*/, this);
    }


    @Override
    //Evento de cálculo de la nueva posición
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Location nuevaPosicion=location;

        //Para calcular distancias entre dos puntos en metros
        //float distancia=location.distanceTo(referencia);
        //Log.d("/posicionamiento"," "+distancia);

        //Paquete de Android para resolver coordenadas a partir de una dirección y
        //viceversa
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());

        List<Address> direccion=null;
        try {
            int x=0;
            direccion = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Altitud en metros sobre el nivel del mar
        //double alt = location.getAltitude();

        txtLat.setText(String.valueOf(lat));
        txtLong.setText(String.valueOf(lng));
        txtSrc.setText("Source = "+provider);
        assert direccion != null;
        txtDir.setText(direccion.get(0).getAddressLine(0));

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle bundle) {
        txtSrc.setText("Source = "+provider);
    }

    @Override
    public void onProviderEnabled(String provider) {
        txtSrc.setText("Source = "+provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        txtSrc.setText("Source = "+provider);
    }

}