package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.to.FunctionSaveTO;
import com.lacey.authority.entity.to.FunctionTO;
import com.lacey.authority.mapper.FunctionMapper;
import com.lacey.authority.service.FunctionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    /**
     * 根据父节点获取功能列表
     * @param parentId
     * @return
     */
    @Override
    public List<FunctionTO> getFunctionListByParentId(String parentId) {

        List<Function> functions=functionMapper.selectList(new QueryWrapper<>());
        List<FunctionTO> functionTOS=new ArrayList<>();
        FunctionTO functionTO=new FunctionTO();

        for (Function function1:functions){
            functionTO.setId(function1.getId());
            functionTO.setName(function1.getName());
            functionTO.setParentId(function1.getParentId());
        }
        functionTOS.add(functionTO);
        return functionTOS;
    }

    /**
     * 新增功能点数据
     * @param functionSaveTO
     * @return
     */
    @Override
    public boolean insertFunction(FunctionSaveTO functionSaveTO) {


        return false;
    }

    /**
     * 封装保存功能表实体类
     * @param functionSaveTO
     * @return
     */
    private Function buildFunction(FunctionSaveTO functionSaveTO){
        Function function=new Function();
        function.setId(StringUtils.isBlank(functionSaveTO.getId())?
                UUID.randomUUID().toString():functionSaveTO.getId());
        function.setName(functionSaveTO.getName());
        function.setCode(functionSaveTO.getCode());
        function.setParentId(functionSaveTO.getParentId());
        return function;
    }

    /**
     * 封装保存功能与角色表实体类
     * @param functionSaveTO
     * @return
     */
    private FunctionRole buileFunctionRole(FunctionSaveTO functionSaveTO){
        FunctionRole functionRole= new  FunctionRole();
        functionRole.setFunctionId(functionSaveTO.getId());
        functionRole.setRoleId(functionSaveTO.getRoleId());
        return functionRole;
    }


}
