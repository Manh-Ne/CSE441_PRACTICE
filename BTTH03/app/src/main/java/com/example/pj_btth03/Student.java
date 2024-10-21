package com.example.pj_btth03;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String birthDay;
    private String address;
    private String email;
    private String major;
    private double gpa;
    private int year;
    private String gender;

    public Student(String id, String name, String birthDay, String address, String email, String major, double gpa, int year, String gender) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.email = email;
        this.major = major;
        this.gpa = gpa;
        this.year = year;
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", BirthDay: " + birthDay + ", Address: " + address + ", Email: " + email + ", Major: " + major + ", GPA: " + gpa + ", Year: " + year + ", Gender: " + gender;
    }
    // Getter và Setter
    public String getId() { return id; }
    public String getName() { return name; }
    public String getBirthDay() { return birthDay; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public int getYear() { return year; }
    public String getGender() { return gender; }
}
