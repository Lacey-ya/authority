package com.lacey.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.entity.to.RoleSaveTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName FunctionRoleMapper
 * @Description 功能角色关联表
 * @Author Lacey
 * @Date 2020-04-08 13:39
 */
public interface FunctionRoleMapper extends BaseMapper<FunctionRole> {

    /**
     * 批量新增数据
     * @param functionRoles
     * @return
     */
    int insertBatch(@Param("functionRoles") List<FunctionRole> functionRoles);

}
