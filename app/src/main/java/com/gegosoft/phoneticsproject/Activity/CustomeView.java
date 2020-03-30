package com.gegosoft.phoneticsproject.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomeView extends View {
    public static final int DEFAULT_BG_COLOR = Color.DKGRAY;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private List<Point> points = new ArrayList<>();
    public CustomeView(Context context , AttributeSet attr) {
        super(context);
    }
    TextView textView;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(32, 32, 32));

        for (Point p : points) {
            Paint paint = new Paint();
            paint.setColor(Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            canvas.drawCircle(p.x, p.y, 50, paint);

        }
    }
    public void clear(){
backgroundColor=DEFAULT_BG_COLOR;
        points.clear();
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point((int)event.getX(), (int)event.getY()));
        textView.setText("A");
        invalidate();
        return true;

    }


}
