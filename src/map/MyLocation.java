package map;

import activities.MapActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import calculations.GeoCalc;


public class MyLocation extends View {
    Paint paint = new Paint();

    public MyLocation(Context context) {
        super(context);
    }

    public MyLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // it gets most opposite horizontal and vertical map values and counts
    // proportion with screen. The bigger value is taken.
    public double countPerPixelScale(Canvas canvas) {
        double geoHeight = MapActivity.maxNorth - MapActivity.maxSouth;
        double geoWidth = MapActivity.maxEast - MapActivity.maxWest;
        double pixelScale;

        if ((geoHeight / getHeight()) > (geoWidth / getWidth())) {
            pixelScale = getHeight() / geoHeight;
            Log.i("bigger", "(geoHeight / getHeight()");
        } else {
            pixelScale = getWidth() / geoWidth;
            Log.i("bigger", "(geoWidth / getWidth())");
        }

        Log.i("geoHeight", String.valueOf(geoHeight));
        Log.i("geoWidth", String.valueOf(geoWidth));
        Log.i("pixelScale", String.valueOf(pixelScale));
        // pixelScale says that one pixel of scale
        return pixelScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackgroundColor(Color.WHITE);
        paint.setColor(Color.RED);
//        Log.i("canvas.getWidth()", String.valueOf(canvas.getWidth()));
//        Log.i("canvas.getHeight()", String.valueOf(canvas.getHeight()));
//        Log.i("getWidth()", String.valueOf(getWidth()));
//        Log.i("getHeight()", String.valueOf(getHeight()));


        drawGrid(canvas);

        if (MapActivity.poiList.size() > 0)
            drawPOITrack(canvas);

    }

    public void drawPOITrack(Canvas canvas) {
        double scale = countPerPixelScale(canvas);

        double xValue, yValue;
        POI first = MapActivity.poiList.get(0);
        POI last = MapActivity.poiList.get(MapActivity.poiList.size() - 1);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2);

        // draw blue circle for first poi
        xValue = (first.getLongitude() - MapActivity.maxWest) * scale;
        yValue = (first.getLatitude() - MapActivity.maxSouth) * scale;
        canvas.drawCircle((float) xValue, (float) yValue, 5, paint);
        // draw blue circle for last poi
        paint.setColor(Color.BLACK);
        xValue = (last.getLongitude() - MapActivity.maxWest) * scale;
        yValue = (last.getLatitude() - MapActivity.maxSouth) * scale;
        canvas.drawCircle((float) xValue, (float) yValue, 5, paint);
        paint.setColor(Color.RED);
        boolean firstPoi = true;
        for (POI poi : MapActivity.poiList) {

            xValue = (poi.getLongitude() - MapActivity.maxWest) * scale;
            yValue = (poi.getLatitude() - MapActivity.maxSouth) * scale;
            canvas.drawPoint((float) xValue, (float) yValue, paint);

            // wypisz dystans punktu atualnego do pierwszego
//            if (firstPoi == false)
//                canvas.drawText(String.format("%.1f",poi.distanceTo(MapActivity.poiList.get(0))), (float) xValue, (float) yValue, paint);
//            firstPoi = false;
        }
        paint.setStrokeWidth(0);


        paint.setTextSize(paint.getTextSize() + 5);
        paint.setColor(Color.BLACK);
        canvas.drawText(String.valueOf(String.format("1 _ =  %.0f m", GeoCalc.countDistance(0, 0, 0, 30 * 1 / scale) * 1000)), getWidth() - 100, getHeight() - 20, paint);
        canvas.drawText(String.valueOf(String.format("vert =  %.2f km", GeoCalc.countDistance(0, 0, 0, (MapActivity.maxNorth - MapActivity.maxSouth)))), getWidth() - 150, getHeight() - 40, paint);
        canvas.drawText(String.valueOf(String.format("hor =  %.2f km", GeoCalc.countDistance(0, 0, 0, (MapActivity.maxEast - MapActivity.maxWest)))), getWidth() - 150, getHeight() - 60, paint);
        paint.setColor(Color.RED);
        paint.setTextSize(paint.getTextSize() - 5);

    }

    public void drawGrid(Canvas canvas) {
        Paint barpPainter = new Paint();
        barpPainter.setColor(Color.GREEN);
        int BAR_SPACE = 30;
        int vertLines = getWidth() / BAR_SPACE;
        int horLines = getHeight() / BAR_SPACE;

        //draw horizontal
        for (int i = 0; i <= horLines; i++) {
            canvas.drawLine(0, BAR_SPACE * i, getWidth(), BAR_SPACE * i, barpPainter);
        }
        //draw vertical
        for (int i = 0; i <= vertLines; i++) {
            canvas.drawLine(BAR_SPACE * i, 0, BAR_SPACE * i, getHeight(), barpPainter);
        }
    }

    public void repaint() {
        this.invalidate();
    }
}
