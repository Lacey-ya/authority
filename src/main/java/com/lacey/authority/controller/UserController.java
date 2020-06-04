package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.to.UserSaveTo;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.RoleListVO;
import com.lacey.authority.entity.vo.UserListVO;
import com.lacey.authority.service.UserService;
import com.lacey.authority.service.impl.UserServiceImpl;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "自学项目",tags = "用户表测试")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户分页列表",notes = "获取用户分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "int",paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "页面容量",required = true,dataType = "int",paramType = "query",example = "1"),
            @ApiImplicitParam(name = "name",value = "模糊查询参数",required = false,dataType = "String",paramType = "query")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/table")
    public String getUserTable(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "name",required = false) String name){
        logger.debug("开始进入controller层的getUserTable方法-------------------------------->");
        CustomPage<UserListVO> customPage = userService.getUserTable(pageNum,pageSize,name);
        return JSON.toJSONString(customPage);
    }

    @ApiOperation(value = "新增用户数据",notes = "新增用户数据")
    @ApiImplicitParams(@ApiImplicitParam(name = "userSaveTo",value = "新增用户数据",required = true,dataType = "UserSaveTo"))
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @PostMapping
    public void insertUser(@RequestBody UserSaveTo userSaveTo){
        logger.debug("开始进入controller层的insertUser方法-------------------------------->");
        userService.insertUser(userSaveTo);
    }





}
