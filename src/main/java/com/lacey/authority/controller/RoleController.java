package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.RoleListVO;
import com.lacey.authority.service.RoleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表",notes = "获取角色分页列表")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "pageNum",value = "当前页码",required = true,dataType = "int",paramType = "query")},
            {@ApiImplicitParam(name = "pageSize",value = "页面容量",required = true,dataType = "int",paramType = "query")},
            {@ApiImplicitParam(name = "name",value = "模糊查询参数（名称）",required = true,dataType = "String",paramType = "query")}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/table")
    public String getRoleTable(@RequestParam("pageNum") int pageNum,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "name", required = false) String name){
        logger.debug("开始进入getRoleTable方法------------------------>");
        CustomPage<RoleListVO> customPage = roleService.getRoleTable(pageNum, pageSize, name);
        return JSON.toJSONString(customPage);
    }
}
