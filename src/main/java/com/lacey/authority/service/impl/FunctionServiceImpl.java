package com.lacey.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lacey.authority.entity.po.Function;
import com.lacey.authority.entity.to.FunctionTO;
import com.lacey.authority.mapper.FunctionMapper;
import com.lacey.authority.service.FunctionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl extends ServiceImpl<FunctionMapper, Function> implements FunctionService {
    @Override
    public List<FunctionTO> getFunctionListByParentId() {
        return null;
    }
}
