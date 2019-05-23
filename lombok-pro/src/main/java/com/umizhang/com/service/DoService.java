package com.umizhang.com.service;

import com.umizhang.com.bean.Student;
import com.umizhang.com.bean.Teacher;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TeacherService
 * @Description TODO
 * @Author umizhang
 * @Date 2019/5/23 12:11
 * @Version 1.0
 */
public class DoService {

    public static void main(String[] args) {
        Map<String, Object> dataInfo = getDataInfo();
        System.out.println("老师信息："+dataInfo.get("teacher"));
        System.out.println("学生信息："+dataInfo.get("student"));
    }


    /**
     * @Author: umizhang
     * @Title: getDataInfo
     * @Description TODO 获取老师和学生信息
     * @Date: 2019/5/23 13:50
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Exception:
     */
    public static Map<String, Object> getDataInfo(){
        Map<String, Object> dataMap = new HashMap<String, Object>();

        // 老师
        Teacher teacher = new Teacher();
        teacher.setTID(10010);
        teacher.setTName("张老师");
        teacher.setTAge(30);
        teacher.setTSex("男");
        teacher.setTAddr("北京市海淀区人名路122号");

        // 学生
        Student student = new Student();
        student.setSID(20010);
        student.setSName("张三");
        student.setSAge(15);
        student.setSSex("女");
        student.setSAddr("上海市徐汇区淮海中路人民大街122号");

        dataMap.put("teacher", teacher);
        dataMap.put("student", student);

        return dataMap;
    }

}
