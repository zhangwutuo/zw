package com.gzg.sysTest.action;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzg.sysTest.entity.Test;
import com.gzg.sysTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GuoZG
 * @since 2019-01-24
 */
@RestController
@RequestMapping("/test")
public class TestAction {

    @Autowired
    private TestService testService;

    @RequestMapping("/query")
    public List<Test> query(){
        System.err.println(JSON.toJSONString(testService.list()));
        return testService.list();
    }

    @RequestMapping("/save")
    public String insert(){
        Test test=new Test();
        test.setId("111");
        test.setName("222");
        if(testService.save(test)){
            return "success";
        }else {
            return "error!";
        }
    }

    @GetMapping("/test3")
    public Test test3() {
        Test test=new Test();
        test.setId("1");
        test.setName("3333");
        testService.saveOrUpdate(test);
        return testService.getById("1");
    }

    @RequestMapping("/update")
    public String update(){
        Test test=new Test();
        test.setId("111");
        test.setName("333");
        if(testService.updateById(test)){
            return "success";
        }else {
            return "error!";
        }
    }


    @RequestMapping("/del")
    public String del(){
        if(testService.removeById(111L)){
            return "success";
        }else {
            return "error!";
        }
    }


    @RequestMapping("/get")
    public Test get(){
        return testService.getById(111);
    }

    @GetMapping("/page")
    public IPage<Test> pagend() {
        //testService.page(new Page<Test>(0, 12), null).getRecords();
        return testService.page(new Page<Test>(0, 12),
                new QueryWrapper<Test>().orderByDesc("id"));
    }

    @GetMapping("/select_wrapper")
    public Object getUserByWrapper() {
        return testService.selectListByWrapper(new QueryWrapper<Test>()
                .lambda().like(Test::getName, "11")
                .or(e -> e.like(Test::getName, "z")));
    }


    @GetMapping("/selectBysql")
    public Object getUserBySql() {
        return testService.selectListBySQL();
    }
}
