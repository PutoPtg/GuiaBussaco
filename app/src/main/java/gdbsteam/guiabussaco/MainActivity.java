package gdbsteam.guiabussaco;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;

import android.location.LocationManager;

import android.os.Vibrator;

import android.os.Bundle;

import android.view.MenuInflater;

import android.view.View;

import android.widget.Toast;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        Resources gpstoast = getResources();
        String gpsToast = gpstoast.getString(R.string.gps_toast);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, gpsToast, Toast.LENGTH_SHORT).show();
        }else{
            showGPSDisabledAlertToUser();
        }




    }

    @Override
    public MenuInflater getMenuInflater() {


        return super.getMenuInflater();
    }

    public void chamaGuia(View view) {

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Intent getNameScreenIntent = new Intent(this, FragmentLayout.class);
        startActivity(getNameScreenIntent);
        
    }

    public void chamaRotas (View view) {

        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Intent getNameScreenIntent = new Intent(this, Rotas.class);
        startActivity(getNameScreenIntent);
    }

    public void settingsCall(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Intent getNameScreenIntent = new Intent(this, SettingsActivity.class);
        startActivity(getNameScreenIntent);
    }

    public void acercaCall(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
        Intent getNameScreenIntent = new Intent(this, Acerca.class);
        startActivity(getNameScreenIntent);
    }

    private void showGPSDisabledAlertToUser(){

        Resources gpswarning = getResources();
        String gpsWarning = gpswarning.getString(R.string.gps_warning);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(gpsWarning)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}

