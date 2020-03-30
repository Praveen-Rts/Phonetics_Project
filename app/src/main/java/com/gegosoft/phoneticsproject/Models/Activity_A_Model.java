package com.gegosoft.phoneticsproject.Models;

public class Activity_A_Model {
    int image;
    String name;

    public Activity_A_Model() {
    }

    public Activity_A_Model(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
