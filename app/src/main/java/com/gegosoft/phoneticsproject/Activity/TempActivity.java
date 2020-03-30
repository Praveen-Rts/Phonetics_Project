package com.gegosoft.phoneticsproject.Activity;

import android.content.Context;
import android.content.Intent;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.gegosoft.phoneticsproject.R;

public class TempActivity extends AppCompatActivity {
    float x = 0;
    float y = 0;
    FrameLayout layout;
    Button refresh;
    CustomView view;
    Intent intent;
    Boolean iscap=true;
    Paint paint;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.templayout);
        layout=(FrameLayout)findViewById(R.id.viewd);
        refresh=new Button(TempActivity.this);

//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//        int height = size.y;
//        Bitmap background_scaled=Bitmap.createScaledBitmap(background_scaled,width, height, true);
//        layout.addView(refresh);
        custometext();
        refresh.setText("Refresh");
refresh.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        refresh.setGravity(Gravity.TOP);
refresh.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        layout.removeAllViews();
        custometext();
    }
});
//layout.addView(refresh);


    }
private void custometext(){
        String trace=getIntent().getStringExtra("letter");

    view = new CustomView(TempActivity.this);
//        view.setTextAlignment();
    view.setText(trace);
    view.setTextSize(300);
    view.setLayoutParams(new FrameLayout.LayoutParams(
            1100,
            1700));
    view.setGravity(Gravity.CENTER);
    layout.addView(view);
    layout.addView(refresh);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public class CustomView extends AppCompatTextView {
        Bitmap mBitmap;
//        Paint paint;
        Paint paint;
        Path path;

        public CustomView(Context context) {
            super(context);
            paint = new Paint();
            path= new Path();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
        }

        protected void onDraw(Canvas canvas) {
            this.setWillNotDraw(false);
            canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            canvas.drawPath(path,paint);
            canvas.drawCircle(x, y, 25, paint);
            super.onDraw(canvas);

//            super.draw(canvas);
        }

        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(event.getX(), event.getY());
                    path.lineTo(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_MOVE:
                    x = event.getX();
                    y = event.getY();
                    path.lineTo(x, y);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    path.lineTo(event.getX(), event.getY());
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}


