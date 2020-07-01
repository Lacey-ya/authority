package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.po.UserRole;
import com.lacey.authority.mapper.FunctionMapper;
import com.lacey.authority.mapper.FunctionRoleMapper;
import com.lacey.authority.mapper.RoleMapper;
import com.lacey.authority.mapper.UserRoleMapper;
import com.lacey.authority.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OpenServiceImpl
 * @Description TODO
 * @Author Lacey
 * 这个类刚才没有交给spring来管理，所以运行的时候初始化报错，说找不到这个bean，就是没有注册给spring
 * 刚才那个继承的写法是因为那几个都是实体类相关的mapper和service类，而且由于用的是mybatis plus，所以是按个写法，
 * 因为要继承mybatis plus封装的那几个增删改查的方法，但是你这个openService只是单纯的一个service类，没有对应的实体类，
 * 所以用不到mybatis plus的那几个增删改查的方法
 * @Date 2020-07-01 15:39
 */
@Service
public class OpenServiceImpl implements OpenService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private FunctionRoleMapper functionRoleMapper;

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public List<String> getFunctionCodesByUser(String userName) {
        //先根据用户名通过（UserRole表）得到角色ID
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("userName", userName));
        List<String> roleId = new ArrayList<>();       //select * from UserRole where userName={}
        for (UserRole userRole : userRoles) {
            roleId.add(userRole.getRoleId());
        }
        //有了角色ID，再通过（FunctionRole表）得到功能ID
        List<FunctionRole> functionRoles = functionRoleMapper.selectList(new QueryWrapper<FunctionRole>().in("roleId", roleId));
        List<String> functionId = new ArrayList<>();
        for (FunctionRole functionRole : functionRoles) {
            functionId.add(functionRole.getFunctionId());
        }
        //有了功能ID，就可以通过（Function表）得到功能code
        List<Function> functions = functionMapper.selectList(new QueryWrapper<Function>().in("id", functionId));
        List<String> functionCodes = new ArrayList<>();
        for (Function function : functions) {
            functionCodes.add(function.getCode());
        }
        return functionCodes;
    }

}
