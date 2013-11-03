package activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.gps_lab3.R;
import map.MyLocation;
import map.POI;

import java.util.ArrayList;


public class MapActivity extends Activity implements LocationListener {

    private LocationManager myLocationManager;
    private LocationListener myLocationLister;
    private POI poi;
    public static ArrayList<POI> poiList = new ArrayList<POI>();
    TextView lattitude;
    TextView longitude;
    TextView distance;
    MyLocation myLocationView;
    public static double maxNorth;
    public static double maxSouth;
    public static double maxEast;
    public static double maxWest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        myLocationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        myLocationLister = this;
        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationLister);

        this.lattitude = (TextView) findViewById(R.id.tv_lattitude);
        this.longitude = (TextView) findViewById(R.id.tv_longitude);
        this.distance = (TextView) findViewById(R.id.tv_dsitance);

        myLocationView = new MyLocation(this);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_mapView);
        ll.addView(myLocationView, ll.getChildCount());


    }

    // lattitude - szerokosc = y
// longitude - dlugosc  = x
    public boolean first = true;

    @Override
    public void onLocationChanged(Location location) {
        this.poi = new POI(location);

        if (first == false) {
            POI lastPoi = poiList.get(poiList.size() - 1);
            POI.distance += this.poi.distanceTo(lastPoi);
            this.distance.setText(String.format("%.2f km, poi: %d", POI.distance / 1000, poiList.size()));
            checkMaximumDimensions(this.poi);
        } else {
            MapActivity.maxNorth = location.getLatitude();
            MapActivity.maxSouth = location.getLatitude();
            MapActivity.maxEast = location.getLongitude();
            MapActivity.maxWest = location.getLongitude();
        }

        this.first = false;
        poiList.add(poi);
        this.lattitude.setText(String.valueOf(poi.getLatitude()));
        this.longitude.setText(String.valueOf(poi.getLongitude()));
        myLocationView.invalidate();
    }


    public void checkMaximumDimensions(POI poi) {
        if (poi.getLongitude() < maxWest) {
            maxWest = poi.getLongitude();
        }
        if (poi.getLongitude() > maxEast) {
            maxEast = poi.getLongitude();
        }
        if (poi.getLatitude() > maxNorth) {
            maxNorth = poi.getLatitude();
        }
        if (poi.getLatitude() < maxSouth) {
            maxSouth = poi.getLatitude();
        }
        Log.i("maxNorth: ", String.valueOf(MapActivity.maxNorth));
        Log.i("maxSouth: ", String.valueOf(MapActivity.maxSouth));
        Log.i("maxEast: ", String.valueOf(MapActivity.maxEast));
        Log.i("maxWest: ", String.valueOf(MapActivity.maxWest));
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

    public void showMap(View view) {
    }

    public void drawGPS(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e("mapDrawGPS", e.toString());
        }
    }
}
