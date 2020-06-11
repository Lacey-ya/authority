package com.lacey.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lacey.authority.entity.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserRoleMapper
 * @Description 用户角色关联表
 * @Author Lacey
 * @Date 2020-04-30 09:55
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量新增数据
     * @param userRoles
     * @return
     */
    int insertBatch(@Param("addRoles")List<UserRole> userRoles);

    /**
     * 修改用户角色关联表中的用户名
     * @param oldName 旧用户名
     * @param newName 新用户名
     * @return
     */
    int updateUserName(@Param("oldName") String oldName, @Param("newName") String newName);
}
