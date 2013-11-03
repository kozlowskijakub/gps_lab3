package com.example.gps_lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jakub
 * Date: 10/27/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GPSStatusView extends View {

    ArrayList<Point> satellitesPositions = new ArrayList<Point>();
    public final float maxRadius = 200;
    private Paint paint = new Paint();

    public ArrayList<GpsSatellite2> satellites = new ArrayList<GpsSatellite2>();

    public GPSStatusView(Context context) {

        super(context);
        satellitesGenerator(satellites);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        Display disp = ((WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

//        TextView tv = (TextView) findViewById(R.id.tv_satellite);

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // draw vertical
        paint.setColor(Color.RED);
        canvas.drawLine(cx, 0, cx, cy * 2, paint);
        // draw horizontal
        canvas.drawLine(0, cy, cx * 2, cy, paint);
        paint.setColor(Color.YELLOW);

        drawRadar(canvas);

        paint.setColor(Color.CYAN);
        int counter = 0;
        paint.setTextSize(paint.getTextSize() + 5);
        for (GpsSatellite2 satellite : satellites) {
            canvas.drawCircle(cx + satellite.xPosition, cy + satellite.yPosition, 2, paint);
            canvas.drawText("/E:" + String.valueOf((int) satellite.getElevation()) + "/A:" + String.valueOf((int) satellite.getAzimuth()), cx + satellite.xPosition, cy + satellite.yPosition - 3, paint);
        }
        paint.setTextSize(paint.getTextSize() - 5);
        paint.setColor(Color.YELLOW);


        this.invalidate();
    }

    public void drawRadar(Canvas canvas) {
        double tab[] = {90, 75, 60, 45, 30, 0};
        int textSize = 7;
        paint.setTextSize(paint.getTextSize() + textSize);
        double radius;
        double cos;
        for (int i = 0; i < tab.length; i++) {
            cos = (double) Math.cos(Math.toRadians(tab[i]));
            radius = (maxRadius * cos);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, (float) radius, paint);
            canvas.drawText(String.valueOf(tab[i]), getWidth() / 2, (getHeight() / 2) - (float) radius - 1, paint);
        }
        paint.setTextSize(paint.getTextSize() - textSize);
    }

    private void countSatellitePositon(GpsSatellite2 satellite) {
        double radius = maxRadius * Math.cos(Math.toRadians(satellite.getElevation()));
        double x = radius * Math.sin(Math.toRadians(satellite.getAzimuth()));
        double y = radius * Math.cos(Math.toRadians(satellite.getAzimuth()));

        satellite.xPosition = (float) x;
        satellite.yPosition = (float) y;
    }


    private void satellitesGenerator(ArrayList<GpsSatellite2> satellites) {
//        Returns the azimuth of the satellite in degrees. The azimuth can vary between 0 and 360.
//        ((GpsSatellite) satellites.get(0)).getAzimuth()
        //Returns the elevation of the satellite in degrees. The elevation can vary between 0 and 90.
//        ((GpsSatellite) satellites.get(0)).getElevation()

        Random rn = new Random();
        int max = 12;
        int min = 4;
        int random = rn.nextInt(max - min) + min;
        float azimuth = 360;
        float elevation = 90;
        GpsSatellite2 satellite;
        for (int i = 0; i < random; i++) {
            satellite = new GpsSatellite2(rn.nextFloat() * azimuth, rn.nextFloat() * elevation);
            countSatellitePositon(satellite);
            satellites.add(satellite);
        }
    }
}

