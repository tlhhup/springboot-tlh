package com.tlh.sys.service.impl;

import com.tlh.sys.entity.Role;
import com.tlh.sys.mapper.RoleMapper;
import com.tlh.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hup on 2017/1/11.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mRoleMapper;

    @Override
    public List<Role> findRoleInfos(Role role) throws Exception {
        return this.mRoleMapper.findRoles(role);
    }
}
