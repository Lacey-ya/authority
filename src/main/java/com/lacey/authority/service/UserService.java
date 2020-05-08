package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.User;
import com.lacey.authority.entity.to.UserSaveTo;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.UserListVO;

public interface UserService extends IService<User> {

    /**
     * 获取用户分页列表
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    CustomPage<UserListVO> getUserTable(int pageNum,int pageSize,String name);

    /**
     * 新增用户数据
     * @param userSaveTo
     * @return
     */
    boolean insertUser(UserSaveTo userSaveTo);

}
