package map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class MyLocation extends View {
    private POI poi;
    public ArrayList<POI> poiList = new ArrayList<POI>();
    Paint paint = new Paint();

    public MyLocation(Context context) {
        super(context);
    }

    public MyLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLocation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyLocation(Context context, POI poi, ArrayList<POI> poiList, Paint paint) {
        super(context);
        this.poi = poi;
        this.poiList = poiList;
        this.paint = paint;
    }

    public MyLocation(Context context, AttributeSet attrs, POI poi, ArrayList<POI> poiList, Paint paint) {
        super(context, attrs);
        this.poi = poi;
        this.poiList = poiList;
        this.paint = paint;
    }

    public MyLocation(Context context, AttributeSet attrs, int defStyle, POI poi, ArrayList<POI> poiList, Paint paint) {
        super(context, attrs, defStyle);
        this.poi = poi;
        this.poiList = poiList;
        this.paint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.

        double xCenter = this.getWidth();
        double YCenter = this.getWidth();

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 100, 100, paint);

//        for (POI poi : poiList) {
//            canvas.drawPoint((float) poi.xCoordinate, (float) poi.yCoordinate, paint);
//        }
//        this.invalidate();
    }
}
