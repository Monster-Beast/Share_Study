package com.jnu.sharestudy.bean;

import com.youth.banner.Banner;

import java.io.Serializable;

public class Goods extends BookBean implements Serializable {
    private static final long serialVersionUID= 1L;
    private int id;
    private String banner;


    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
