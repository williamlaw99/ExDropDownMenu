package com.williamlaw.exddm.bean;

/**
 * Created by Administrator on 2017/8/28.
 */
public class DropdownItemBean {

    public int id;

    public String text;
    public String value;
    public String suffix;
    public DropdownItemBean(String text, int id, String value) {
        this.text = text;
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
