package activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.gps_lab3.R;
import map.POI;

import java.util.ArrayList;


public class MapActivity extends Activity implements LocationListener {

    private LocationManager myLocationManager;
    private LocationListener myLocationLister;
    private POI poi;
    public ArrayList<POI> poiList = new ArrayList<POI>();
    Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mapview);
            myLocationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
            myLocationLister = this;
            myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocationLister);
        } catch (Exception e) {
            Log.e("myException", e.toString());
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void drawGps(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }


    @Override
    public void onLocationChanged(Location location) {
        this.poi = new POI();
        this.poi.xCoordinate = location.getLatitude();
        this.poi.yCoordinate = location.getLongitude();
        this.poiList.add(poi);

    }

//
//    public void drawGps(View v) {
//        GPSStatusView gpsStatusView = new GPSStatusView(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_main);
//
//        if (draw == false)
//            ll.removeViewAt(ll.getChildCount() - 1);
//        this.draw = false;
//        ll.addView(gpsStatusView, params);
//    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderEnabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onProviderDisabled(String provider) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
