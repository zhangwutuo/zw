package com.gzg.sysTest.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gzg.sysTest.entity.Test;
import com.gzg.sysTest.mapper.TestMapper;
import com.gzg.sysTest.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GuoZG
 * @since 2019-01-24
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Resource
    private TestMapper testMapper;


    @Override
    public List<Test> selectListByWrapper(Wrapper wrapper) {
        return testMapper.selectListByWrapper(wrapper);
    }

    @Override
    public List<Test> selectListBySQL() {
        return testMapper.selectListBySQL();
    }

    @Override
    public List<Object> selectObjs(Wrapper<Test> queryWrapper) {
        return testMapper.selectObjs(queryWrapper);
    }
}
