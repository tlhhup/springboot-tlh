package com.tlh.test;

import com.tlh.TlhApplication;
import com.tlh.sys.entity.Role;
import com.tlh.sys.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by hup on 2017/1/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TlhApplication.class)
public class RoleServiceTest {

    @Autowired
    private RoleService mRoleService;

    @Test
    public void findRoles() throws Exception {
        List<Role> roles = mRoleService.findRoleInfos(null);
    }

}
