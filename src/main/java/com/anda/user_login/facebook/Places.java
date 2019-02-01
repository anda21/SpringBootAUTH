package com.anda.user_login.facebook;

import lombok.Data;

import javax.persistence.Entity;

@Data
//@Entity
public class Places {

    private String name;
    private Long checkins;
    private Long id;
    private Picture picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCheckins() {
        return checkins;
    }

    public void setCheckins(Long checkins) {
        this.checkins = checkins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
