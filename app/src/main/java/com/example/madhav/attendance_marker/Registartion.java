package com.example.madhav.attendance_marker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registartion extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5,e6;
    DatabaseHelper db;
    String email,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);
        db=new DatabaseHelper(this);
        setContentView(R.layout.activity_registartion);
        e1=(EditText) findViewById(R.id.fname);
        e2=(EditText) findViewById(R.id.lname);
        e3=(EditText) findViewById(R.id.contact);
        e4=(EditText) findViewById(R.id.dob);
        e5=(EditText) findViewById(R.id.email);
        e6=(EditText) findViewById(R.id.password);

    }
    public void submitClicked(View view){
        String fname=e1.getText().toString();
        String lname=e2.getText().toString();
        String contact=e3.getText().toString();
        String dob=e4.getText().toString();
        String mail=e5.getText().toString();
        String pass=e6.getText().toString();

        // Error Setting(VALIDATIONS)
        int flag=0;
        if(e1.getText().toString().trim().equalsIgnoreCase("") || (Pattern.matches("[a-zA-Z]",e1.getText().toString()))) {
            Toast.makeText(this.getApplicationContext(),"Invalid First Name",Toast.LENGTH_SHORT).show();
            e1.setError("Invalid First Name");
            flag=1;

        }
        if( e2.getText().toString().trim().equalsIgnoreCase("")&& (Pattern.matches("[a-zA-Z]",e2.getText().toString())
        )){
            Toast.makeText(this.getApplicationContext(),"Invalid Last Name",Toast.LENGTH_SHORT).show();
            e2.setError("Invalid Last Name");
            flag=1;
        }

        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches())){
            e5.setError("Email Format Invalid");
            flag=1;

        }

        if (e6.getText().toString().trim().equalsIgnoreCase("")){
            e6.setError(" Password cannot be empty");
            flag=1;
        }

        if (e3.getText().toString().trim().equalsIgnoreCase("")||(e3.getText().toString().length()!=10)){
            e3.setError(" Contact Invalid");
            flag=1;
        }
        if (e4.getText().toString().trim().equalsIgnoreCase("")|| (e4.getText().toString().length()!=8)){
            e4.setError(" Dob cannot be empty");
            flag=1;
        }

        if (flag!=1){

            info i = new info(fname,lname,contact,dob,mail,pass);
            try {
               // Long in = db.addInfo(i);
            }catch (Exception e)
            {
                Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
            Log.d("error" , i.toString());
            Intent intent = new Intent(this, Launcher_Welcome.class);
            startActivity(intent);

            Toast.makeText(this, "Submittt", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();

        }
    }

}
