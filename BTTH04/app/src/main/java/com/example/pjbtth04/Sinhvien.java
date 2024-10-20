package com.example.pjbtth04;

import java.io.Serializable;

public class Sinhvien implements Serializable {
    private String masv;

    private String hoten;
    private String lop;
    private double diem;

    public Sinhvien() {}
    public Sinhvien(String hoten, String lop, double diem, String masv) {
        this.hoten = hoten;
        this.lop = lop;
        this.diem = diem;
        this.masv = masv;
    }

    public String getMasv() {return masv;}

    public void setMasv(String masv) {this.masv = masv;}

    public String getHoten() {return hoten;}

    public void setHoten(String hoten) {this.hoten = hoten;}

    public String getLop() {return lop;}

    public void setLop(String lop) {this.lop = lop;}

    public double getDiem() {return diem;}

    public void setDiem(double diem) {this.diem = diem;}
}

