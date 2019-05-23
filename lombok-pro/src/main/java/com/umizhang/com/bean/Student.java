package com.umizhang.com.bean;

import lombok.*;

/**
 * @ClassName Student
 * @Description TODO
 * @Author umizhang
 * @Date 2019/5/23 12:04
 * @Version 1.0
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * 學生ID
     */
    private Integer sID;
    /**
     * 學生名字
     */
    private String sName;
    /**
     * 學生年龄
     */
    private Integer sAge;
    /**
     * 學生性别
     */
    private String sSex;
    /**
     * 學生住址
     */
    private String sAddr;
}
