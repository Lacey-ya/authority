package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName Open
 * @Description 对外提供的接口
 * @Author Lacey
 * @Date 2020-07-01 15:36
 */
public interface OpenService {

    /**
     * 对外提供根据用户名获取功能点接口
     * @param userName
     * @return
     */
    List<String> getFunctionCodesByUser(String userName);

}
