package com.tlh.sys.service;

import com.tlh.sys.entity.Role;

import java.util.List;

/**
 * Created by hup on 2017/1/11.
 */
public interface RoleService {

    List<Role> findRoleInfos(Role role) throws Exception;

}
