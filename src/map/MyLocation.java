package map;

import activities.MapActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class MyLocation extends View {

    //    public ArrayList<POI> poiList = new ArrayList<POI>();
    Paint paint = new Paint();


    public MyLocation(Context context) {
        super(context);
//        this.setId(10);
    }

    public MyLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
//        this.setId(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
        setBackgroundColor(Color.YELLOW);
        double xCenter = this.getWidth();
        double YCenter = this.getWidth();

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(100, 100, 100, paint);
//        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        float OFFSET = 50;
        for (POI poi : MapActivity.poiList) {
            canvas.drawPoint((float) (poi.xCoordinate - 49.84f) * 5000 + 6 * OFFSET, (float) (poi.yCoordinate - 19f) * 5000 + OFFSET, paint);

        }

        try {
            canvas.drawText((String.valueOf((MapActivity.poiList.get(MapActivity.poiList.size() - 1).xCoordinate - 49.84f) * 2500)), 100, 100, paint);
            canvas.drawText((String.valueOf((MapActivity.poiList.get(MapActivity.poiList.size() - 1).yCoordinate - 19f) * 2500)), 100, 150, paint);
        } catch (Exception e) {
            Log.e("myException", e.toString());
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }


    public void repaint() {
        this.invalidate();
    }
}
