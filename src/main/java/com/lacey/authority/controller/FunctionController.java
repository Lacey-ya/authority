package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.to.FunctionTO;
import com.lacey.authority.service.FunctionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "自学项目",tags = "功能表测试")
@RestController
@RequestMapping("/api")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @ApiOperation(value = "获取功能列表",notes = "根据父节点获取功能列表")
    @ApiImplicitParam(name = "parentId",value = "父节点id",required = false,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/function/list")
    public String getFunctionList(@RequestParam(required = false,value = "parentId") String parentId){
        List<FunctionTO> functionTOS = functionService.getFunctionListByParentId(parentId);
        return JSON.toJSONString(functionTOS);
    }


}
