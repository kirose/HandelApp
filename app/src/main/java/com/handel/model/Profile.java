package com.handel.model;

import java.io.Serializable;

/**
 * Created by Marco Antonio on 29/12/2017.
 */

public class Profile implements Serializable{
    private Long idProfile;
    private String name;
    private String img;
    private String email;
    private String phone;
    private String score;

    public Profile() {
    }

    public Profile(Long idProfile, String name, String img, String email, String phone, String score) {
        this.idProfile = idProfile;
        this.name = name;
        this.img = img;
        this.email = email;
        this.phone = phone;
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
