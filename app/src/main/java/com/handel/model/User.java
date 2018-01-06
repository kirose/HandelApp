package com.handel.model;

import java.io.Serializable;

/**
 * Created by Marco Antonio on 29/12/2017.
 */

public class User implements Serializable{
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
