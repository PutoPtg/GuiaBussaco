package gdbsteam.guiabussaco.map;

import android.support.v4.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import gdbsteam.guiabussaco.R;


public class MapFragment extends Fragment{

    private Context context;
    private View v;
    private MapView map;

    private IMapController mapController;
    private GeoPoint startPoint;
    private GeoPoint userLocation;
    private MyLocationNewOverlay mLocationOverlay;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        v = inflater.inflate(R.layout.map_fragment,null);
        startPoint = new GeoPoint(40.192756, -8.4143277);

        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setMaxZoomLevel(12d);

        mapController = map.getController();
        mapController.setZoom(12d);

        mapController.setCenter(startPoint);

        map = v.findViewById(R.id.osm_view);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);


        startPoint = new GeoPoint(40.192756, -8.4143277);

        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setMaxZoomLevel(12d);

        Log.i("haha", "onViewCreated");

        mapController = map.getController();
        mapController.setZoom(12d);

        mapController.setCenter(startPoint);

    }
}
