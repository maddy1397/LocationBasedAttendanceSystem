package com.example.madhav.attendance_marker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class frag_leave extends Fragment  {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    EditText number,date,reason;
    String phoneNo="9899797342";
    Button sub;
    String message;
    View rview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rview=inflater.inflate(R.layout.frag_leave, container, false);
        number=(EditText) rview.findViewById(R.id.number);
        date=(EditText) rview.findViewById(R.id.date);
        sub=(Button) rview.findViewById(R.id.button3);
        reason=(EditText) rview.findViewById(R.id.reason);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r=reason.getText().toString();
                String no=number.getText().toString();
                String d=date.getText().toString();
                message="Leave Request for "+ no+" days from"+ d+"due to "+r+"\n \t by Madhav";
                    sendEmail();

//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNo));
//                intent.putExtra("sms_body", message);
//                startActivity(intent);
            }
        });
        return rview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "IN ON CREATE FRAG", Toast.LENGTH_SHORT).show();

    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"madhavg1310@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Leave Application");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public   void sendsms(){

        String r=reason.getText().toString();
        String no=number.getText().toString();
        String d=date.getText().toString();
        message="Leave Request for "+ no+"of days from"+ date+"due to "+reason;

                if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(), // Activity
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);

            }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(this.getContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


}