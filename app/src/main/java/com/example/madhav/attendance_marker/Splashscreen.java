package com.example.madhav.attendance_marker;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class Splashscreen extends AppCompatActivity{
    Intent intent;
    protected boolean _active = true;
    protected int _splashTime = 2000;
    ImageView imageView;
    AnimationDrawable anim;
    @Override
        protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        try
        {
            setContentView(R.layout.activity_splashscreen);
            imageView=(ImageView) findViewById(R.id.imageview);
            if(imageView==null) throw new AssertionError();
            imageView.setBackgroundResource(R.drawable.animation_loading);
            anim= (AnimationDrawable) imageView.getBackground();
            anim.start();
            intent=new Intent(this,Launcher_Welcome.class);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    finish();
                    startActivity(intent);
                }
            }, _splashTime);
        }catch(Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
//
//        EasySplashScreen config=new EasySplashScreen(Splashscreen.this)
//                .withFullScreen()
//                .withTargetActivity(Launcher_Welcome.class)
//                .withSplashTimeOut(4000)
//                .withBackgroundColor(Color.BLACK)
//                .withLogo(R.drawable.editsoftlogo)
//                .withHeaderText("Hello EditSofter!!")
//                .withFooterText("Let's Go To Punch Our Attendance")
//                .withBeforeLogoText("EditSoft")
//                .withAfterLogoText("Creative. Passionate. Inspired. Innovative.");
//
//        config.getHeaderTextView().setTextColor(Color.WHITE);
//        config.getFooterTextView().setTextColor(Color.WHITE);
//        config.getAfterLogoTextView().setTextColor(Color.WHITE);
//        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
//
//        View view=config.create();
//
//        setContentView(view);
    }
}