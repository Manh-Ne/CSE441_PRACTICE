package com.example.pj_prac03;

public class Country {
    private int flag;
    private String name;
    private String countryCapital;
    private int population;
    private double area;
    private double density;
    private double worldShare;

    public Country(String name, String countryCapital, int flag, int population, double area, double density, double worldShare) {
        this.name = name;
        this.countryCapital = countryCapital;
        this.flag = flag;
        this.population = population;
        this.area = area;
        this.density = density;
        this.worldShare = worldShare;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getWorldShare() {
        return worldShare;
    }

    public void setWorldShare(double worldShare) {
        this.worldShare = worldShare;
    }
}
