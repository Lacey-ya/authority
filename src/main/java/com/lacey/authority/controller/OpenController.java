package com.lacey.authority.controller;

import com.alibaba.fastjson.JSON;
import com.lacey.authority.entity.vo.UserListVO;
import com.lacey.authority.service.OpenService;
import com.lacey.authority.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName OpenController
 * @Description TODO
 * @Author Lacey
 * @Date 2020-07-01 15:43
 */

@Api(value = "自学项目",tags = "对外提供接口")
@RestController
@RequestMapping("/api/open")
public class OpenController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OpenService openService;

    @ApiOperation(value = "对外提供根据用户名获取功能点接口",notes = "对外提供根据用户名获取功能点接口")
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String",paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功",response =String.class),
            @ApiResponse(code = 500,message = "失败",response = String.class)})
    @GetMapping("/getActionByUser")
    public String getActionByUser(@RequestParam String userName){
        logger.debug("开始进入controller层的getActionByUser方法-------------------------------->");
        List<String> functionCodes = openService.getFunctionCodesByUser(userName);
        return JSON.toJSONString(functionCodes);
    }
}
