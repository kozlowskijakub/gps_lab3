package activities;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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
    MyLocation ml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        myLocationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        myLocationLister = this;
        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationLister);

        this.lattitude = (TextView) findViewById(R.id.tv_lattitude);
        this.longitude = (TextView) findViewById(R.id.tv_longitude);
        ml = new MyLocation(this);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_mapView);
        ll.addView(ml, ll.getChildCount());
    }


    @Override
    public void onLocationChanged(Location location) {
        this.poi = new POI();
        this.poi.xCoordinate = location.getLatitude();
        this.poi.yCoordinate = location.getLongitude();
        poiList.add(poi);

        this.lattitude.setText(String.valueOf(poi.yCoordinate));
        this.longitude.setText(String.valueOf(poi.xCoordinate));
        ml.invalidate();
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
