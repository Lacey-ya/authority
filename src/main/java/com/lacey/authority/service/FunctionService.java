package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.to.FunctionSaveTO;
import com.lacey.authority.entity.to.FunctionTO;

import java.util.List;

public interface FunctionService extends IService<Function> {


    /**
     * 根据父节点获取功能列表，
     * 此方法根据parentId做条件判断，然后分出两个逻辑，
     * 1、若parentId为空，则只查询数据库中parentId为空的数据，即根节点数据，因为没有父节点；
     * 2、若parentId不为空，则查询parentId值为传入的parentId参数的值的数据，即此id的子节点。
     * @param parentId 传入的父节点id
     * @return
     */
    List<FunctionTO> getFunctionListByParentId(String parentId);

    /**
     * 新增功能点数据
     * @param functionSaveTO
     * @return
     */
    boolean insertFunction(FunctionSaveTO functionSaveTO);

    /**
     * 修改功能数据
     * @param id
     * @return
     */
    boolean updateFunction(String id);

}
