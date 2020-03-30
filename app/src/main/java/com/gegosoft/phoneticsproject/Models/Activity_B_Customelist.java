package com.gegosoft.phoneticsproject.Models;

import java.io.Serializable;

public class Activity_B_Customelist implements Serializable {
    public static final long serialID = 1234L;
    public int image;
    public String name;

    public Activity_B_Customelist() {
    }

    public Activity_B_Customelist(int image, String name) {
        this.image = image;
        this.name = name;
    }


}
