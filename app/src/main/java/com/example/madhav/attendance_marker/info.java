package com.example.madhav.attendance_marker;

public class info {
    private String fname;
    private String lname;
    private String contact;
    private String dob;
    private String email;
    private String pass;

    public info(){
    }

    public info(String fname , String lname ,String contact,String dob,String email,String pass) {
        this.fname=fname;
        this.lname=lname;
        this.contact=contact;
        this.dob=dob;
        this.email=email;
        this.pass=pass;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//----------------------------------------------------------


    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getContact() {
        return contact;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
