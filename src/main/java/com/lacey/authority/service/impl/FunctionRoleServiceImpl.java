package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.FunctionRole;
import com.lacey.authority.mapper.FunctionRoleMapper;
import com.lacey.authority.service.FunctionRoleService;
import org.springframework.stereotype.Service;

@Service
public class FunctionRoleServiceImpl extends ServiceImpl<FunctionRoleMapper, FunctionRole> implements FunctionRoleService {
}
