package com.example.gps_lab3;

import android.app.Activity;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Iterator;

public class MainActivity extends Activity implements GpsStatus.Listener {

    LocationManager locationManager = null;
    boolean draw = true;


    public void drawGps(View v) {
        GPSStatusView gpsStatusView = new GPSStatusView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_main);

        if (draw == false)
            ll.removeViewAt(ll.getChildCount() - 1);
        this.draw = false;
        ll.addView(gpsStatusView, params);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_main);

//        this.satellites = locationManager.gps

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.addGpsStatusListener(this);

    }

    @Override
    public void onGpsStatusChanged(int event) {
        GpsStatus gpsStatus = locationManager.getGpsStatus(null);
        String strGpsStats = null;

        if (gpsStatus != null) {
            Iterable<GpsSatellite> satellites = gpsStatus.getSatellites();
            Iterator<GpsSatellite> sat = satellites.iterator();
            int i = 0;
            while (sat.hasNext()) {
                GpsSatellite satellite = sat.next();
                strGpsStats += (i++) + ": "
                        + satellite.getPrn()
                        + ", " + satellite.usedInFix()
                        + "," + satellite.getSnr()
                        + "," + satellite.getAzimuth()
                        + "," + satellite.getElevation()
                        + "\n\n";
            }
        }
    }


}
