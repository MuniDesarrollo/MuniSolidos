package com.example.carlin.munisolidos.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by CARLIN on 13/03/2018.
 */

public class Ubicacion implements LocationListener{


    private  Context ctx;
    String proveedor;
    LocationManager locationManager;
    private  boolean networkOn;

    public Ubicacion(Context ctx) {
        this.ctx = ctx;
        locationManager= (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        proveedor=LocationManager.GPS_PROVIDER;
        networkOn=locationManager.isProviderEnabled(proveedor);
        locationManager.requestLocationUpdates(proveedor,1000,1,this);
        getLocation();
    }

    private  void getLocation()
    {
        if (networkOn)
        {
            Location lo=locationManager.getLastKnownLocation(proveedor);
            if (lo!=null)
            {
                StringBuilder builder=new StringBuilder();
                builder.append("Altitud :").append(lo.getAltitude())
                .append("Longitud :").append(lo.getLongitude());
                Toast.makeText(ctx,builder.toString(),Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        getLocation();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
