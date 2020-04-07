package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.to.FunctionSaveTO;
import com.lacey.authority.entity.to.FunctionTO;

import java.util.List;

public interface FunctionService extends IService<Function> {


    /**
     * 根据父节点获取功能列表
     * @param parentId
     * @return
     */
    List<FunctionTO> getFunctionListByParentId(String parentId);

    /**
     * 新增功能点数据
     * @param functionSaveTO
     * @return
     */
    boolean insertFunction(FunctionSaveTO functionSaveTO);



}
