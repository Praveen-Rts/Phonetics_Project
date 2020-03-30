package com.gegosoft.phoneticsproject.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gegosoft.phoneticsproject.MainActivity;
import com.gegosoft.phoneticsproject.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {
    Timer RunSplash = new Timer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash_screen);
        TimerTask ShowSplash = new TimerTask(){
            @Override
            public void run() {
                Intent intent=new Intent(Splash_Screen.this,MainActivity.class);
                startActivity(intent);

            }
        };
        RunSplash.schedule(ShowSplash, 3000);
    }
    }




    //    private static final long SPLASH_SCREEN_TIME_OUT = 2000;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //This method is used so that your splash activity
//        //can cover the entire screen.
//        setContentView(R.layout.splash_screen);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i=new Intent(Splash_Screen.this,
//                        MainActivity.class);
//                //Intent is used to switch from one activity to another.
//
//                startActivity(i);
//                //invoke the SecondActivity.
//
//                finish();
//                //the current activity will get finished.
//            }
//        }, SPLASH_SCREEN_TIME_OUT);
//    }



