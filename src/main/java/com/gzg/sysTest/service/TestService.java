package com.gzg.sysTest.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gzg.sysTest.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author GuoZG
 * @since 2019-01-24
 */
public interface TestService extends IService<Test> {
    List<Test> selectListByWrapper(Wrapper wrapper);

    List<Test> selectListBySQL();
}
