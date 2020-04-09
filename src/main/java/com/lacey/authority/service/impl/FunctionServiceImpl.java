package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.to.FunctionSaveTO;
import com.lacey.authority.entity.to.FunctionTO;
import com.lacey.authority.mapper.FunctionMapper;
import com.lacey.authority.mapper.FunctionRoleMapper;
import com.lacey.authority.service.FunctionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    @Autowired
    private FunctionRoleMapper functionRoleMapper;

    @Override
    public List<FunctionTO> getFunctionListByParentId(String parentId) {
        // 首先，判断parentId的值
        List<Function> functions = new ArrayList<>();
        if (StringUtils.isBlank(parentId)) {
            // 开始写parentId为空时的逻辑，即查询根节点数据
            // 这个生成的SQL语句就是：select * from function where parentId is null;
            functions = functionMapper.selectList(new QueryWrapper<Function>().isNull("parentId"));
        }else {
            // 开始写parentId不为空时的逻辑，即根据parentId查询子节点数据
            // 这个逻辑的SQL语句应为：select * from function where parentId = #{parentId};
            // 上面的SQL语句后面的#{parentId}就是传入的parentId参数，开始敲代码实现
            functions = functionMapper.selectList(new QueryWrapper<Function>().eq("parentId",parentId));
        }
        // 以上就是根据parentId获取数据的所有逻辑了，
        // 现在已经获取到从数据库查出的基础数据functions，
        // 然后要转成前端需要的字段
        if (CollectionUtils.isEmpty(functions)){
            return new ArrayList<>();
        }
        List<FunctionTO> functionTOS = new ArrayList<>();
        for (Function function:functions){
            FunctionTO functionTO = new FunctionTO();
            functionTO.setId(function.getId());
            functionTO.setName(function.getName());
            functionTO.setParentId(function.getParentId());
            functionTOS.add(functionTO);
        }
        return functionTOS;
    }

    /**
     * 新增功能点数据
     * @param functionSaveTO
     * @return
     */
    @Override
    public boolean insertFunction(FunctionSaveTO functionSaveTO) {
        //向功能表中插入数据
        Function function = buildFunction(functionSaveTO);
        int result = functionMapper.insert(function);
        if (result<0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFunction(String id) {

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


}
