package com.centit.test;

import com.alibaba.fastjson.JSON;
import com.centit.framework.staticsystem.po.RoleInfo;
import com.centit.framework.staticsystem.po.UserInfo;
import com.centit.framework.staticsystem.po.UserUnit;
import com.centit.framework.staticsystem.security.StaticCentitUserDetails;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUserDetailsJson {
    @Test
    public void createUserDetails() throws Exception {
        StaticCentitUserDetails userDetails = new StaticCentitUserDetails();
        UserInfo userInfo = new UserInfo(
                "anonymousUser",
                "T",
                "anonymousUser",
                "anonymousUser");
        List<UserUnit> uus = new ArrayList<>();
        UserUnit uu = new UserUnit("00001", "nq","zz","F");
        uu.setUserCode("anonymousUser");
        uu.setUnitCode("U00001");
        uus.add(uu);

        uu = new UserUnit("00002", "gl","zr","T");
        uu.setUserCode("anonymousUser");
        uu.setUnitCode("U00002");
        uus.add(uu);

        userInfo.setUserUnits(uus);
        List<RoleInfo> roles = new ArrayList<>(2);
        RoleInfo roleInfo = new RoleInfo("anonymous", "匿名用户角色","G",
                "U00001","T","匿名用户角色");
        roles.add(roleInfo);
        roleInfo = new RoleInfo("admin", "管理员角色","G",
                "U00002","T","管理员角色");
        roles.add(roleInfo);

        userDetails.setUserInfo(userInfo);

        userDetails.setAuthoritiesByRoles(roles);

        String s = JSON.toJSONString(userDetails);

        System.out.println(s);

        userDetails = JSON.parseObject(s,StaticCentitUserDetails.class);
        s = JSON.toJSONString(userDetails);

        System.out.println(s);
    }
}
