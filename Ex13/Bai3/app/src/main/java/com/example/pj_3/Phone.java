package com.example.pj_3;

public class Phone {
    private String namePhone;
    private int imagePhone;

    public Phone(String namePhone, int imagePhone) {
        this.namePhone = namePhone;
        this.imagePhone = imagePhone;
    }

    public String getNamePhone() {
        return namePhone;
    }

    public void setNamePhone(String namePhone) {
        this.namePhone = namePhone;
    }

    public int getImagePhone() {
        return imagePhone;
    }

    public void setImagePhone(int imagePhone) {
        this.imagePhone = imagePhone;
    }
}
