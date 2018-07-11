package gdbsteam.guiabussaco;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import gdbsteam.guiabussaco.map.MapFragment;


public class Rotas extends AppCompatActivity {

    private MapFragment mapFragment;
    private final String MAP_FRAG_TAG = "mapfrag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotas);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        mapFragment = MapFragment.newInstance();

        transaction.add(R.id.mapview, mapFragment, MAP_FRAG_TAG);
        transaction.commit();

    }
}
