package com.tlh.sys.entity;

import java.io.Serializable;

/**
 * Created by ping on 2016/12/25.
 */
public class Role implements Serializable {

    private int id;
    private String roleName;
    private String description;
    private  boolean available;//是否可用

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
