package com.example.probarmapa;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.probarmapa.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private LatLng previa;
    private LatLng ultima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();

                ultima = latLng;

                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                googleMap.addMarker(markerOptions);

                double lat = latLng.latitude;
                double lng = latLng.longitude;
                Location loc = new Location("");
                loc.setLatitude(lat);
                loc.setLongitude(lng);
                previa = new LatLng(loc.getLatitude(),loc.getLongitude());
                double distance = SphericalUtil.computeDistanceBetween(previa, ultima);
                //Toast.makeText(getApplicationContext(), "posicion: " + lat + "-" + lng, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "distancia: " + distance, Toast.LENGTH_SHORT).show();
            }
        });
/*        LatLng adra = new LatLng(36.75, -3);
        mMap.addMarker(new MarkerOptions().position(adra).title("Adra"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(adra));*/
    }
}