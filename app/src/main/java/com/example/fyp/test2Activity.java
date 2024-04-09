package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class test2Activity extends AppCompatActivity implements LocationListener {
    Location location;
    LocationManager locMgr;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        locMgr=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        location=locMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    }

    @SuppressLint("MissingPermission")
    protected void onResume(){
        super.onResume();
        locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10000,
                10,
                this);
    }

    protected void onPause(){
        super.onPause();
        locMgr.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
//        showLocation(location);
    }

//    private void showLocation(Location location){
//        if (location==null){
//            tvLatitude.setText("Latitude : unknown");
//            tvLongitude.setText("Longitude : unknown");
//        }else{
//            tvLatitude.setText("Latitude : "+location.getLatitude());
//            tvLongitude.setText("Longitude : "+location.getLongitude());
//        }
//    }

}

