package com.gegosoft.phoneticsproject.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DottedPaintView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private static final int TOUCH_TOLERANCE_DP = 20;
    private static final int BACKGROUND = 0xFFDDDDDD;
    private List<Point> mPoints = new ArrayList<Point>();
    private int mLastPointIndex = 0;
    private int mTouchTolerance;
    private boolean isPathStarted = false;

//    public DottedPaintView(Context context) {
//        super(context);
//        mCanvas = new Canvas();
//        mPath = new Path();
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(12);
//        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
//
//        // TODO just test points
//        Point p1 = new Point(548, 258);
//        Point p2 = new Point(449, 505);
//        Point p3 = new Point(362, 744);
//        Point p4 = new Point(275, 983);
//        Point p5 = new Point(189, 1231);
//        Point p6 = new Point(131, 1482);
//        Point p7 = new Point(65, 1721);
//        mPoints.add(p1);
//        mPoints.add(p2);
//        mPoints.add(p3);
//        mPoints.add(p4);
//        mPoints.add(p5);
//        mPoints.add(p6);
//        mPoints.add(p7);
//    }

    public DottedPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);

        // TODO just test points
        Point p5 = new Point(189, 1231);
        Point p4 = new Point(275, 983);
        Point p3 = new Point(362, 744);
        Point p2 = new Point(449, 505);
        Point p1 = new Point(548, 258);




        Point p6 = new Point(131, 1482);
        Point p7 = new Point(65, 1721);
        mPoints.add(p5);
        mPoints.add(p4);
        mPoints.add(p3);
        mPoints.add(p2);
        mPoints.add(p1);




//        mPoints.add(p6);
//        mPoints.add(p7);

//        Point p1a = new Point(548, 258);
        Point p8 = new Point(638, 505);
        Point p9 = new Point(733, 749);
        Point p10 = new Point(787, 979);
        Point p11 = new Point(865, 1247);
//        Point p12 = new Point(915, 1486);
//        Point p13 = new Point(977, 1713);
//        mPoints.add(p1a);
        mPoints.add(p8);
        mPoints.add(p9);
        mPoints.add(p10);
        mPoints.add(p11);
//        mPoints.add(p12);
//        mPoints.add(p13);


    }
//    public DottedPaintView(Context context) {
//        super(context);
//        mCanvas = new Canvas();
//        mPath = new Path();
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
//        mPaint.setStrokeWidth(12);
//        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
//
//        // TODO just test points
//        Point p1 = new Point(548, 258);
//        Point p8 = new Point(638, 505);
//        Point p9 = new Point(733, 749);
//        Point p10 = new Point(787, 979);
//        Point p11 = new Point(865, 1247);
//        Point p12 = new Point(915, 1486);
//        Point p13 = new Point(977, 1713);
//        mPoints.add(p1);
//        mPoints.add(p8);
//        mPoints.add(p9);
//        mPoints.add(p10);
//        mPoints.add(p11);
//        mPoints.add(p12);
//        mPoints.add(p13);
//    }

    public DottedPaintView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        clear();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(BACKGROUND);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);

        // TODO remove if you dont want points to be drawn
        for (Point point : mPoints) {
            canvas.drawPoint(point.x, point.y, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private void touch_start(float x, float y) {

        if (checkPoint(x, y, mLastPointIndex)) {
            mPath.reset();
            // user starts from given point so path can beis started
            isPathStarted = true;
        } else {
            // user starts move from point which doen's belongs to mPinst list
            isPathStarted = false;
        }

    }

    //ADDED WITH LAST EDIT
    private void touch_move(float x, float y) {
// draw line with finger move
        if (isPathStarted) {
            mPath.reset();
            Point p = mPoints.get(mLastPointIndex);
            mPath.moveTo(p.x, p.y);
            if (checkPoint(x, y, mLastPointIndex + 1)) {
                p = mPoints.get(mLastPointIndex + 1);
                mPath.lineTo(p.x, p.y);
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                ++mLastPointIndex;
            } else {
                mPath.lineTo(x, y);
            }
        }
    }
    /**
     * Draws line.
     */
    private void touch_up(float x, float y) {
        mPath.reset();
        if (checkPoint(x, y, mLastPointIndex + 1) && isPathStarted) {
            // move finished at valid point so draw whole line

            // start point
            Point p = mPoints.get(mLastPointIndex);
            mPath.moveTo(p.x, p.y);
            // end point
            p = mPoints.get(mLastPointIndex + 1);
            mPath.lineTo(p.x, p.y);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
            // increment point index
            ++mLastPointIndex;
            isPathStarted = false;
        }

    }

    /**
     * Sets paint
     *
     * @param paint
     */
    public void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    /**
     * Returns image as bitmap
     *
     * @return
     */
    public Bitmap getBitmap() {
        return mBitmap;
    }

    /**
     * Clears canvas
     */
    public void clear() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(BACKGROUND);
        mCanvas.setBitmap(mBitmap);
        invalidate();
    }

    /**
     * Checks if user touch point with some tolerance
     */
    private boolean checkPoint(float x, float y, int pointIndex) {
        if (pointIndex == mPoints.size()) {
            // out of bounds
            return false;
        }
        Point point = mPoints.get(pointIndex);
        //EDIT changed point.y to poin.x in the first if statement
        if (x > (point.x - mTouchTolerance) && x < (point.x + mTouchTolerance)) {
            if (y > (point.y - mTouchTolerance) && y < (point.y + mTouchTolerance)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getPoints() {
        return mPoints;
    }

    public void setPoints(List<Point> points) {
        this.mPoints = points;
    }

    private int dp2px(int dp) {
        Resources r = getContext().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }}