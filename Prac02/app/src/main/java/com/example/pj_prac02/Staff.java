package com.example.pj_prac02;

public class Staff {
    private int id;
    private String name;
    private String birthDay;
    private String salary;

    public Staff(int id, String name, String birthDay, String salary){
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
