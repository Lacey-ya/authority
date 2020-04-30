package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.to.RoleSaveTO;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.RoleListVO;
import com.lacey.authority.service.RoleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "自学项目",tags = "角色表测试")
@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;


    @ApiOperation(value = "获取角色分页列表",notes = "获取角色分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "int",paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页面容量",required = true,dataType = "int",paramType = "query",example = "1"),
            @ApiImplicitParam(name = "name",value = "模糊查询参数",required = false,dataType = "String",paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/table")
    public String getRoleTable(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "name",required = false) String name){
        logger.debug("开始进入controller层的getRoleTable方法-------------------------------->");
        CustomPage<RoleListVO> customPage = roleService.getRoleTable(pageNum,pageSize,name);
        return JSON.toJSONString(customPage);
    }

    @ApiOperation(value = "新增角色以及和功能的绑定数据",notes = "新增角色以及和功能的绑定数据")
    @ApiImplicitParam(name = "roleSaveTO",value = "新增数据",required = true,dataType = "RoleSaveTO",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PostMapping
    public void insertRole(@RequestBody RoleSaveTO roleSaveTO){
        logger.debug("开始进入controller层的insertRole方法-------------------------------->");
        roleService.insertRole(roleSaveTO);
    }

    @ApiOperation(value = "修改角色数据以及绑定的功能数据",notes = "修改角色数据以及绑定的功能数据")
    @ApiImplicitParam(name = "roleSaveTO",value = "修改数据",required = true,dataType = "RoleSaveTO",paramType = "body")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PutMapping
    public void updateRole(@RequestBody RoleSaveTO roleSaveTO){
        logger.debug("开始进入controller层的updateRole方法-------------------------------->");
        roleService.updateRole(roleSaveTO);
    }

    @ApiOperation(value = "删除角色数据以及绑定的功能和用户数据",notes = "删除角色数据以及绑定的功能和用户数据")
    @ApiImplicitParam(name = "id",value = "要删除的角色id",required = true,dataType = "String",paramType = "path")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") String id){
        logger.debug("开始进入controller层的deleteRole方法-------------------------------->");
        roleService.deleteRole(id);
    }

    @ApiOperation(value = "根据id获取角色详情数据",notes = "根据id获取角色详情数据")
    @ApiImplicitParam(name = "id",value = "获取角色详情的id",required = true,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/detail")
    public String getRoleDetailListById(@RequestParam String id){
        logger.debug("开始进入controller层的getRoleDetailListById方法-------------------------------->");
        RoleSaveTO roleSaveTO = roleService.getRoleDetailListById(id);
        return JSON.toJSONString(roleSaveTO);
    }

}
