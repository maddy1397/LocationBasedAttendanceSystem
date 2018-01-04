package com.example.madhav.attendance_marker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static android.provider.Settings.Secure.LOCATION_MODE_HIGH_ACCURACY;

public class Dashboard extends AppCompatActivity {

    private final String LOG_TAG="EDITSOFT ATTENDANCE";
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Context context;
    int locationMode;
    AlertDialog.Builder builder;

    public Dashboard() throws Settings.SettingNotFoundException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null);
//            }
//        });
         context=getApplicationContext();
        try {
            locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
//            Toast.makeText(getApplicationContext(),e.printStackTrace(),Toast.LENGTH_SHORT).show();
            Log.d("EXCEPTIONN DASHBOARD",e.getMessage());
        }
        builder = new AlertDialog.Builder(Dashboard.this);
        builder.setTitle("LOCATION UPDATE")
                .setMessage("Update Location Access to HIGH ACCURACY FIRST?")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(context,"Location will not be accurate",Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog alertDialog=builder.create();


        if(locationMode == LOCATION_MODE_HIGH_ACCURACY) {
            //request location updates
        } else {
            alertDialog.show();
        }
      //
        Toast.makeText(this,"IN MAIN DASHBOARD",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    frag_puncher puncher=new frag_puncher();
                    return puncher;
                case 1:
                    frag_meter  meter=new frag_meter();
                    return meter;
                case 2:
                    frag_leave leave=new frag_leave();
                    return leave;
            }
            return null;

            }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Puncher ";
                case 1:
                    return "Meter";
                case 2:
                    return "Leave";
            }
            return null;
        }
    }
}
