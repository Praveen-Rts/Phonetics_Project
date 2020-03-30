package com.gegosoft.phoneticsproject.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.gegosoft.phoneticsproject.R;

public class Draw_Dots_Activity extends AppCompatActivity {

    float x = 0;
    float y = 0;
    FrameLayout layout;
    Button refresh;
    CustomeView view;
    ImageView dots;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawdots_layout);
        DrawingView mDrawingView=new DrawingView(this);
        LinearLayout mDrawingPad=(LinearLayout)findViewById(R.id.view_drawing_pad);
        mDrawingPad.addView(mDrawingView);
//        View v = new MyCanvas(getApplicationContext());
//        Bitmap bitmap = Bitmap.createBitmap(500/*width*/, 500/*height*/, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        v.draw(canvas);
//        dots=findViewById(R.id.drawimage);
//        dots.setImageBitmap(bitmap);

//customeimage();
    }
    class DrawingView extends View {
        Paint       mPaint;
        //MaskFilter  mEmboss;
        //MaskFilter  mBlur;
        Bitmap  mBitmap;
        Canvas  mCanvas;
        Path    mPath;
        Paint   mBitmapPaint;

        public DrawingView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setColor(0xFFFF0000);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(20);

            mPath = new Path();
            mBitmapPaint = new Paint();
            mBitmapPaint.setColor(Color.RED);
        }
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
        @Override
        public void draw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.draw(canvas);
            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
            canvas.drawPath(mPath, mPaint);
        }
        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 4;

        private void touch_start(float x, float y) {
            //mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }
        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
                mX = x;
                mY = y;
            }
        }
        private void touch_up() {
            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            //mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
            // kill this so we don't double draw
            mPath.reset();
            // mPath= new Path();
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
                    touch_up();
                    invalidate();
                    break;
            }
            return true;
        }
    }




//    public class MyCanvas extends View {
//        public MyCanvas(Context context) {
//            super(context);
//            // TODO Auto-generated constructor stub
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            // TODO Auto-generated method stub
//            super.onDraw(canvas);
//            Paint pBackground = new Paint();
//            pBackground.setColor(Color.WHITE);
//            canvas.drawRect(0, 0, 512, 512, pBackground);
//            Paint pText = new Paint();
//            pText.setColor(Color.BLACK);
//            pText.setTextSize(20);
//            canvas.drawText("Sample Text", 100, 100, pText);
//        }
//    }
//private void customeimage(){
//    view = new CustomeView(Draw_Dots_Activity.this);
////        view.setTextAlignment();
//    view.setImageResource(R.drawable.dots);
//
//    view.setLayoutParams(new FrameLayout.LayoutParams(
//            1100,
//            1700));
////    view.setGravity(Gravity.CENTER);
//    layout.addView(view);
//
//
//
//}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
//    public class CustomeView extends AppCompatImageView {
//        Bitmap mBitmap;
//        Paint paint;
//        Path path;
//        public CustomeView(Context context) {
//            super(context);
//            paint = new Paint();
//            path= new Path();
//            paint.setColor(Color.BLUE);
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(5);
//        }
//
////        protected void onDraw(Canvas canvas) {
////            this.setWillNotDraw(false);
////            canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
////            canvas.drawPath(path,paint);
////            canvas.drawCircle(x, y, 25, paint);
////            super.onDraw(canvas);
////
////        }
////        public boolean onTouchEvent(MotionEvent event) {
////            int action = event.getAction();
////            switch (action) {
////                case MotionEvent.ACTION_DOWN:
////                    path.moveTo(event.getX(), event.getY());
////                    path.lineTo(event.getX(), event.getY());
////                    break;
////                case MotionEvent.ACTION_MOVE:
////                    x = event.getX();
////                    y = event.getY();
////                    path.lineTo(x, y);
////                    invalidate();
////                    break;
////                case MotionEvent.ACTION_UP:
////                    path.lineTo(event.getX(), event.getY());
////                    break;
////                case MotionEvent.ACTION_CANCEL:
////                    break;
////                default:
////                    break;
////            }
////            return true;
////        }
//
//    }


}
