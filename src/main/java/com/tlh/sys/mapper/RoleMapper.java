package com.tlh.sys.mapper;

import com.tlh.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by hup on 2017/1/8.
 */
@Mapper
public interface RoleMapper {

    int saveRole(Role role) throws Exception;

    int deleteRole(Role role) throws Exception;

    int updateRole(Role role) throws Exception;

    List<Role> findRoles(Role role) throws Exception;

}
