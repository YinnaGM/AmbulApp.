package com.example.ambulapp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);


        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {


                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.addMarker(new MarkerOptions().position(miUbicacion).title("Mi ubicación"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(miUbicacion)
                                .zoom(14)
                                .bearing(90)
                                .tilt(45)
                                .build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                };

            }
        });



        return view;
    }
}
