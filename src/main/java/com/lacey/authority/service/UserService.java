package com.lacey.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.po.User;
import com.lacey.authority.entity.to.UserSaveTo;
import com.lacey.authority.entity.vo.CustomPage;
import com.lacey.authority.entity.vo.UserListVO;
import com.lacey.authority.entity.vo.UserRoleListVO;
import com.lacey.authority.entity.vo.UserRoleUpVO;

import java.util.List;

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

    /**
     * 修改用户数据
     * @param userRoleUpVO
     * @return
     */
    boolean updateUser(UserRoleUpVO userRoleUpVO);

    /**
     * 删除用户数据
     * @param id
     * @return
     */
    boolean deleteUser(String id);

    /**
     * 根据id获取用户详细数据
     * @param id
     * @return
     */
    UserListVO getUserDetailListById(String id);

}
