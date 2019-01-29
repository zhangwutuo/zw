package com.gzg.sysTest.action;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzg.sysTest.entity.Test;
import com.gzg.sysTest.service.TestService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return testService.list();
    }


    @RequestMapping("/dtsql")
    public String dtsql(){
        System.out.println(
                new SQL(){
                    {
                        INNER_JOIN("sys_test");
                        VALUES("id,name","10001,testinsert");
                    }
                }.toString()
        );
        return "success!";
    }

    @RequestMapping("/save")
    public String insert(){
        Test test=new Test("1111","222");
        if(testService.save(test)){
            return "success";
        }else {
            return "error!";
        }
    }

    @GetMapping("/test3")
    public Test test3() {
        Test test=new Test("1","3333");
        testService.saveOrUpdate(test);
        return testService.getById("1");
    }

    @RequestMapping("/update")
    public String update(){
        Test test=new Test();
        test.setId("222");
        test.setName("乐观锁22");
        test.setVersion(testService.getById(test.getId()).getVersion());
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



    @RequestMapping("/delmap")
    public String delmap(){
        Map<String,Object> map=new HashMap<>();
        map.put("id","1");
        if(testService.removeByMap(map)){
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


    @RequestMapping("/del2")
    public String del2(){
        testService.remove(
                new QueryWrapper<Test>()
                        .lambda().like(Test::getId,"1")
        );
        return "success!";
    }


    @RequestMapping("/del3")
    public String del3(){
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        testService.removeByIds(list);
        return "success!";
    }

    @RequestMapping("/update2")
    public String update2() {
        testService.update(
                new Test("444","ttttt"),
                new QueryWrapper<Test>()
                    .eq("id","333")
        );
        return "success!";
    }

    @RequestMapping("/count")
    public Integer count(){
        return testService.count(
                new QueryWrapper<Test>().eq("id","222")
        );
    }


    @RequestMapping("/selectlist")
    public List<Test> selectlist(){
        return testService.selectListByWrapper(
                new QueryWrapper<Test>()
                        .lambda().like(Test::getName,"z")
        );
    }
    @RequestMapping("/queryobjs")
    public List<Object> queryobjs(){
        return testService.selectObjs(
                new QueryWrapper<Test>()
                .lambda().like(Test::getName,"z")
        );
    }
}
