package gdbsteam.guiabussaco;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class Rotas extends Activity {

    //private MapView mapView;
    //private MapController mapController;
    private MyLocationNewOverlay myLocationOverlay = null;
    private MyItemizedOverlay myItemizedOverlay = null;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.rotas);
        MapView map = (MapView) findViewById(R.id.mapview);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(40.38541, -8.38220);
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        mapController.setCenter(startPoint);
        //Marker startMarker = new Marker(map);
        //startMarker.setPosition(startPoint);
        //startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        //map.getOverlays().add(startMarker);



        //startMarker.setIcon(getResources().getDrawable(android.R.drawable.star_big_on));
        //startMarker.setTitle("Start point");
        map.invalidate();




        myLocationOverlay = new MyLocationNewOverlay(this, map);
        map.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableMyLocation();
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        map.getOverlays().add(myScaleBarOverlay);

        Drawable marker = getResources().getDrawable(android.R.drawable.star_big_on);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);
        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
        myItemizedOverlay = new MyItemizedOverlay(marker, resourceProxy);
        map.getOverlays().add(myItemizedOverlay);


        Resources pontos = getResources();
        String[] pts = pontos.getStringArray(R.array.pontos);
        int [] coordn = pontos.getIntArray(R.array.gpsn);
        int [] coordw = pontos.getIntArray(R.array.gpsw);
        ArrayList<GeoPoint> listOfPoints = new ArrayList<GeoPoint>();
        ArrayList<Marker> listOfMarkers = new ArrayList<Marker>();

        for(int i = 0; i<(pts.length); i++) {
            Log.i("tag", "i = " + i);
            GeoPoint oneP = new GeoPoint(((double)coordn[i])/100000, ((double)coordw[i])/100000);
            listOfPoints.add(oneP);
            Marker mark = new Marker(map);
            listOfMarkers.add(mark);

            listOfMarkers.get(i).setPosition(listOfPoints.get(i));
            listOfMarkers.get(i).setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            listOfMarkers.get(i).setIcon(getResources().getDrawable(android.R.drawable.star_big_on));
            listOfMarkers.get(i).setTitle(pts[i]);

        }

        for(int i = 0; i<(pts.length); i++) {
            map.getOverlays().add(listOfMarkers.get(i));

        }

        map.invalidate();
        /*marcMaker(map);
        map.invalidate();*/

        /*Marker gH = new Marker(map);
        GeoPoint grandHotel = new GeoPoint(40.38475, -8.37838);
        gH.setPosition(grandHotel);
        gH.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(gH);
        gH.setIcon(getResources().getDrawable(android.R.drawable.star_big_on));
        gH.setTitle("Grand Hotel de Luso");
        map.invalidate();

        GeoPoint ermMAlto = new GeoPoint(40.28105, -8.28090);
        myItemizedOverlay.addItem(ermMAlto, "Ermida do Mont'Alto", "Ermida do Mont'Alto");

        GeoPoint museuMoinho = new GeoPoint(40.30231, -8.30607);
        myItemizedOverlay.addItem(museuMoinho, "Museu do Moinho", "Museu do Moinho");

        GeoPoint porOliveira = new GeoPoint(40.30479, -8.30665);
        myItemizedOverlay.addItem(porOliveira, "Portela da Oliveira", "Portela da Oliveira");

        GeoPoint pelCarvalho = new GeoPoint(40.32954, -8.31320);
        myItemizedOverlay.addItem(pelCarvalho, "Pelourinho de Carvalho", "Pelourinho de Carvalho");

        GeoPoint palaceHotel = new GeoPoint(40.37618, -8.36498);
        myItemizedOverlay.addItem(palaceHotel, "Pálace Hotel", "Pálace Hotel");

        GeoPoint cruzAlta = new GeoPoint(40.37107, -8.36576);
        myItemizedOverlay.addItem(cruzAlta, "Cruz Alta", "Cruz Alta");

        GeoPoint obelisco = new GeoPoint(40.37634, -8.35787);
        myItemizedOverlay.addItem(obelisco, "Monumento Gerra Peninsular", "Monumento Gerra Peninsular");

        GeoPoint pCW = new GeoPoint(40.35060, -8.34608);
        myItemizedOverlay.addItem(pCW, "Posto de Comando Wellington", "Posto de Comando Wellington");

        GeoPoint termas = new GeoPoint(40.38408, -8.37727);
        myItemizedOverlay.addItem(termas, "Termas de Luso", "Termas de Luso");*/

    }

    void marcMaker(MapView map){

        Resources pontos = getResources();
        String[] pts = pontos.getStringArray(R.array.pontos);
        int [] coordn = pontos.getIntArray(R.array.gpsn);
        int [] coordw = pontos.getIntArray(R.array.gpsw);
        ArrayList<GeoPoint> listOfPoints = new ArrayList<GeoPoint>();
        ArrayList<Marker> listOfMarkers = new ArrayList<Marker>();

        for(int i = 0; i<(pts.length); i++) {
            Log.i("tag", "i = " + i);
            GeoPoint oneP = new GeoPoint(coordn[i], coordw[i]);
            listOfPoints.add(oneP);
            Marker marker = new Marker(map);
            listOfMarkers.add(marker);

            listOfMarkers.get(i).setPosition(listOfPoints.get(i));
            listOfMarkers.get(i).setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            listOfMarkers.get(i).setIcon(getResources().getDrawable(android.R.drawable.star_big_on));
            listOfMarkers.get(i).setTitle(pts[i]);
            map.getOverlays().add(listOfMarkers.get(i));
            map.invalidate();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.enableFollowLocation();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myLocationOverlay.disableMyLocation();
        myLocationOverlay.disableFollowLocation();
    }
}
