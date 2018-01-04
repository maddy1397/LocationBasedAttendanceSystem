package com.example.madhav.attendance_marker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Launcher_Welcome extends AppCompatActivity {

    Intent intent,intent1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //case R.id.navigation_home:
                   // Intent intent=new Intent(this,);
                  //  return true;vig
                case R.id.sign_up:
                    startActivity(intent);
                    return true;
                case R.id.help:
                    startActivity(intent1);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        intent= new Intent(this,Registartion.class);
        intent1=new Intent(this,Help.class);
    }
    public void login(View view){
        Toast.makeText(this,"TAP ON FINGERPRINT SENSOR",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,Dashboard.class);
        startActivity(intent);
    }
    public void onetouchlogin(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Toast.makeText(this,"TAP ON FINGERPRINT SENSOR",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,fingerprint.class);
            startActivity(intent);
        }

    }


}
