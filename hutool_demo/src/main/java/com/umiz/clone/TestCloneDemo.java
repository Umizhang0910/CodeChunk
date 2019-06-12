package com.umiz.clone;

import cn.hutool.core.util.ObjectUtil;

/**
 * @ClassName TestCloneDemo
 * @Description TODO
 * @Author umizhang
 * @Date 2019/6/12 14:52
 * @Version 1.0
 */
public class TestCloneDemo {

    public static void main(String[] args) {
        DemoHuToolClone resource = new DemoHuToolClone();
        resource.setId(10010);
        resource.setText("测试文本信息！");

        DemoHuToolClone clone = resource.clone();
//        clone.setId(10011);
//        clone.setText("测试文本信息2！");

        System.out.println(resource.toString());
        System.out.println(clone.toString());
        System.out.println(resource == clone);
        System.out.println("-------");
        System.out.println(clone.getText().equals(resource.getText()));
        System.out.println(clone.getId().equals(resource.getId()));
        /*
         * java比较两个对象是否相等，实现：
         *      可以通过重写equals()实现。
         *      1、==比较的是地址，而equals()比较的是对象内容
         *      2、重写equals()方法必须重写hashCode()
         */
        System.out.println(resource.equals(clone));


        DemoHuToolClone2 source = new DemoHuToolClone2();
        source.setId(10012);
        source.setText("测试文本信息3！");
        DemoHuToolClone2 clone2 = source.clone();
//        clone2.setId(10014);
//        clone2.setText("测试文本信息4！");

        System.out.println(source.toString());
        System.out.println(clone2.toString());
        System.out.println(source == clone2);
        System.out.println(clone2.getText().equals(source.getText()));
        System.out.println(clone2.getId().equals(source.getId()));

        System.out.println(source.equals(clone2));


        DemoHuToolDeepClone deepSource = new DemoHuToolDeepClone();
        deepSource.setId(1994);
        deepSource.setText("深度克隆对象");
        deepSource.setDemoDeepClone(new DemoDeepClone("测试的信息"));

        DemoHuToolDeepClone deepClone = ObjectUtil.cloneByStream(deepSource);
//        deepClone.setId(1995);
//        deepClone.setText("深度克隆对象copy！");

        System.out.println(deepSource);
        System.out.println(deepClone);
        System.out.println(deepSource == deepClone);
        System.out.println(deepSource.equals(deepClone));

        System.out.println("-------");
        System.out.println(deepSource.getText().equals(deepClone.getText()));
        System.out.println(deepSource.getId().equals(deepClone.getId()));
        System.out.println(deepSource.getDemoDeepClone().equals(deepClone.getDemoDeepClone()));
    }
}
