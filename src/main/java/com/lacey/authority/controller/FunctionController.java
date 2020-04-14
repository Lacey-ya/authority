package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.to.FunctionSaveTO;
import com.lacey.authority.entity.to.FunctionTO;
import com.lacey.authority.service.FunctionService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "自学项目",tags = "功能表测试")
@RestController
@RequestMapping("/api/function")
public class FunctionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FunctionService functionService;

    @ApiOperation(value = "获取功能列表",notes = "根据父节点获取功能列表")
    @ApiImplicitParam(name = "parentId",value = "父节点id",required = false,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/list")
    public String getFunctionList(@RequestParam(value = "parentId", required = false) String parentId){
        logger.debug("开始进入getFunctionList方法---------->parentId=" + parentId);
        List<FunctionTO> functionTOS = functionService.getFunctionListByParentId(parentId);
        return JSON.toJSONString(functionTOS);
    }

    @ApiOperation(value = "新增一条功能点数据",notes = "新增一条功能点数据")
    @ApiImplicitParam(name = "functionSaveTO",value = "新增数据",required = true,dataType = "FunctionSaveTO",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PostMapping
    public void insertFunction(@RequestBody FunctionSaveTO functionSaveTO){
        logger.debug("开始进入insertFunction方法---------->");
        functionService.insertFunction(functionSaveTO);
    }

    @ApiOperation(value = "修改功能点数据",notes = "修改功能点数据")
    @ApiImplicitParam(name = "function",value = "修改的数据",required = true,dataType = "Function",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PutMapping
    public void updateFunction(@RequestBody Function function){
        logger.debug("开始进入updateFunction方法---------->");
        functionService.updateFunction(function);
    }

    @ApiOperation(value = "删除功能点数据",notes = "删除功能点数据")
    @ApiImplicitParam(name = "id",value = "删除id",required = true,dataType = "String",paramType = "path")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @DeleteMapping("/{id}")
    public void deleteFunction(@PathVariable("id") String id){
        logger.debug("开始进入deleteFunction方法---------->id"+id);
        functionService.deleteFunction(id);
    }

    @ApiOperation(value = "根据ID获取功能点数据",notes = "根据ID获取功能点数据")
    @ApiImplicitParam(name = "id",value = "id",required = true,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/detail")
    public String getFunctionDetailById(@RequestParam String id){
        Function functions=functionService.getFunctionDetailById(id);
        return JSON.toJSONString(functions);
    }

}
