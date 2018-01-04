package com.example.madhav.attendance_marker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;



public class frag_puncher extends Fragment implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks ,
        LocationListener{
    Button button,button1;
    private final String LOG_TAG="EDITSOFT ATTENDANCE";
    private Dashboard.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private TextView txtOutput1,txtOutput2,txtOutput3,txt;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;
    Location center,current_location,test,college;
    double distance,distance2,distance3;
    double max_distance=100.000000;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"IN ON CREATE FRAG",Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(), // Activity
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

        }
        center= new Location("Editsoft Office");
        current_location=new Location("Current");
        test=new Location("HANSI_Home");
        college=new Location("NCU");
        college.setLatitude(28.503559);
        college.setLongitude(77.049766);
        test.setLatitude(29.100780);
        test.setLongitude(75.966521);
        center.setLatitude(28.514688);
        center.setLongitude(77.229808);
    }
    // Get permission result
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted

                } else {
                    // permission was denied
                }
                return;
            }
        }
    }

    @Override
    public void onStop() {
mGoogleApiClient.disconnect();
        super.onStop();
        }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(),"IN ON CREATEVIEW FRAG",Toast.LENGTH_SHORT).show();
        View RootView=inflater.inflate(R.layout.frag_puncher, container, false);
        button= (Button) RootView.findViewById(R.id.button2);
        button1=(Button) RootView.findViewById(R.id.button3);
        txtOutput1=(TextView) RootView.findViewById(R.id.latt);
        txtOutput2=(TextView) RootView.findViewById(R.id.abc);
        txtOutput3=(TextView) RootView.findViewById(R.id.address);
        txt=(TextView) RootView.findViewById(R.id.textView9);
        mGoogleApiClient=new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distance=center.distanceTo(current_location);
                distance2=test.distanceTo(current_location);
                distance3=college.distanceTo(current_location);
                if(distance<=max_distance || distance2 <=max_distance || distance3<=max_distance){
                    txt.setText("Success!!! You have Punched IN");
                    Toast.makeText(getActivity(),"SUCCESS"+distance+"\n"+distance2,Toast.LENGTH_SHORT).show();
                }else{
                    txt.setText("You are currently "+distance+ " meters away from OFFICE PREMISES");
                    Toast.makeText(getActivity(),"You are currently not in OFFICE PREMISES"+distance,Toast.LENGTH_SHORT).show();
                }

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distance=center.distanceTo(current_location);
                distance2=test.distanceTo(current_location);
                distance3=college.distanceTo(current_location);
                if(distance<=max_distance || distance2<=max_distance || distance3<=max_distance){
                    txt.setText("Success!!! You have Punched OUT");
                }else{
                    txt.setText("You are currently "+distance+ " meters away from OFFICE PREMISES");
                }
            }
        });

        return RootView;
    }


    @Override
    public void onConnected(Bundle bundle) {
    mLocationRequest=LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
       // mLocationRequest.setExpirationTime(5000);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, (LocationListener) this);

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getActivity(),"CONNECTION SUSPENDED",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(getActivity(),"Connection  Failed ",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location){
        txtOutput1.setText(String.valueOf(location.getLatitude()));
        txtOutput2.setText(String.valueOf(location.getLongitude()));
        txtOutput3.setText(getCompleteAddressString(location.getLatitude(),location.getLongitude()));
        current_location.setLatitude(location.getLatitude());
        current_location.setLongitude(location.getLongitude());
    }
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My loction address", "" + strReturnedAddress.toString());
            } else {
                Log.w("My  loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My  loction address", "Canont get Address!");
        }
        return strAdd;
    }
}
