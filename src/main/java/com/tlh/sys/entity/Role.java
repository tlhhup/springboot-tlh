package com.tlh.sys.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ping on 2016/12/25.
 */
public class Role implements Serializable {

    private int id;
    private String roleName;
    //与权限的多对多关系
    private List<Right> rights;

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

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
}
