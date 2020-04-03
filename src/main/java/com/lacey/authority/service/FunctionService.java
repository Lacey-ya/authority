package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.to.FunctionTO;

import java.util.List;

public interface FunctionService extends IService<Function> {

        /**
        *@Description 
        *@Param
        *@Return 
        *@Author Lacey
        *@Date 2020-04-03
        *@Time 15:56
        */
    List<FunctionTO> getFunctionListByParentId();


}
