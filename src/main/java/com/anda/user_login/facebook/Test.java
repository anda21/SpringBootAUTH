package com.anda.user_login.facebook;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
//@Entity
public class Test {
    private List<Places> data;

    public List<Places> getData() {
        return data;
    }

    public void setData(List<Places> data) {
        this.data = data;
    }
}
