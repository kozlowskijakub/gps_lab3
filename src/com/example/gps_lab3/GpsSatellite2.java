package com.example.gps_lab3;

import android.location.GpsSatellite;

/**
 * Created with IntelliJ IDEA.
 * User: jakub
 * Date: 10/28/13
 * Time: 10:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class GpsSatellite2 {
    private float azimuth;
    private float elevation;
    public float xPosition;
    public float yPosition;
    public int number;

    public GpsSatellite2(float azimuth, float elevation) {
        this.azimuth = azimuth;
        this.elevation = elevation;
    }

    public float getAzimuth() {
        return azimuth;
    }

    public float getElevation() {
        return elevation;
    }
}
