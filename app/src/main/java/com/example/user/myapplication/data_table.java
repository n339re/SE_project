package com.example.user.myapplication;


public class data_table {

    private long ID ;
    private int PREDICT ;
    private int C_NUM;
    private String Dept;
    private String Doctor;

    public data_table() {}
    public data_table(long id, String dept,String doctor,int n,int p ) {
        this.Dept = dept;
        this.Doctor = doctor;
        this.ID = id;
        this.C_NUM = n;
        this.PREDICT = p;
    }
    public void setID(long id) {
        this.ID = id;
    }
    public long getID() {
        return this.ID;
    }

    public void setDept(String name) {
        this.Dept = name;
    }
    public String getDept() {
        return this.Dept;
    }

    public void setDoctorname (String name2) {
        this.Doctor = name2;
    }
    public String getDoctorname() {
        return this.Doctor;
    }

    public void setNum(int n) {
        this.C_NUM   = n;
    }
    public int getNum() {
        return this.C_NUM;
    }

    public void setP(int p) {this.PREDICT = p; }
    public int getP() {return this.PREDICT; }

}

