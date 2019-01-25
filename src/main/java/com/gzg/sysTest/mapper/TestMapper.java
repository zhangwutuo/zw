package com.gzg.sysTest.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.gzg.sysTest.SuperMapper;
import com.gzg.sysTest.entity.Test;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GuoZG
 * @since 2019-01-24
 */
public interface TestMapper extends SuperMapper<Test> {

    List<Test> selectListByWrapper(@Param("ew") Wrapper wrapper);

    @Select("select * from sys_test")
    List<Test> selectListBySQL();
}
