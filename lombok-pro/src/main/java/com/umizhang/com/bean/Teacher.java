package com.umizhang.com.bean;

import lombok.*;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author umizhang
 * @Date 2019/5/23 12:04
 * @Version 1.0
 */
@Data
public class Teacher {

    /**
     * 老师ID
     */
    private Integer tID;
    /**
     * 老师名字
     */
    private String tName;
    /**
     * 老师年龄
     */
    private Integer tAge;
    /**
     * 老师性别
     */
    private String tSex;
    /**
     * 老师住址
     */
    private String tAddr;
}
