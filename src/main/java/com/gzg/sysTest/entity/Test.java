package com.gzg.sysTest.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author GuoZG
 * @since 2019-01-24
 */
@TableName("sys_test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    //自动增长时加上
    //@TableId(value = "id",type = IdType.AUTO)
    private String id;

    private String name;
    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test() {
    }

    public Test(String id, String name, Integer deleted, Integer version) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
        this.version = version;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
