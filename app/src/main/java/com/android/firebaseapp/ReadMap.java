package com.android.firebaseapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadMap extends FragmentActivity implements OnMapReadyCallback {
    DatabaseReference reff;
  // long maxid = 0;
    int ster = 1;



    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       final MyApp myApp = (MyApp) this.getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_map);
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
        long counti = getIntent().getLongExtra("count",0);
        Double i = getIntent().getDoubleExtra("latitude_1",36.56248397073886139);
        Double k = getIntent().getDoubleExtra("longitude_1",139.88580666482449);

        mMap = googleMap;
        LatLng gen =new LatLng(i,k);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gen, 16));



        while (ster != counti) {
            reff = FirebaseDatabase.getInstance().getReference().child("トイレ").child(String.valueOf(ster));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //Double関数でのデータベースからの抜き出し
                    Double id = (Double) dataSnapshot.child("double1").getValue();
                    Double kd = (Double) dataSnapshot.child("double2").getValue();
                    String name=dataSnapshot.child("name").getValue().toString();


                    LatLng zh = new LatLng(id, kd);
                    mMap.addMarker(new MarkerOptions().position(zh).title(name));
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
            ster++;
        }


    }
}
