package com.example.pjbtth04;

public class Student {
    private String name;
    private String msv;
    private String className;
    private double gpa;

    public Student(String name, String msv, String className, double gpa) {
        this.name = name;
        this.msv = msv;
        this.className = className;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", msv='" + msv + '\'' +
                ", className='" + className + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
