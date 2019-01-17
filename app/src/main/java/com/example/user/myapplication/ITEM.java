package com.example.user.myapplication;
import java.util.Date;
public class ITEM {

    private long ID;
    private String deptname;
    private String doctorname;
    private int num;
    private String date;
    private String slot;

    public ITEM() {}
    public ITEM(long id, String dept,String doctor,int n,String d,String s) {
        this.deptname = dept;
        this.doctorname = doctor;
        this.ID = id;
        this.num = n;
        this.date = d;
        this.slot = s;
    }
    public void setID(long id) {
        this.ID = id;
    }
    public long getID() {
        return this.ID;
    }

    public void setDeptname (String name) {
        this.deptname = name;
    }
    public String getDeptname() {
        return this.deptname;
    }

    public void setDoctorname (String name2) {
        this.doctorname = name2;
    }
    public String getDoctorname() {
        return this.doctorname;
    }

    public void setNum(int n) {
        this.num = n;
    }
    public int getNum() {
        return this.num;
    }

    public void setDate(String d) {this.date = d; }
    public String getDate() {return this.date; }

    public void setSlot(String s) {this.slot = s; }
    public String getSlot() {return this.slot; }
}
