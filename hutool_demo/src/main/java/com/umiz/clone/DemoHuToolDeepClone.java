package com.umiz.clone;

import cn.hutool.core.util.ObjectUtil;

import java.io.Serializable;

/**
 * @ClassName DemoHuToolDeepClone
 * @Description TODO 深克隆
 * @Author umizhang
 * @Date 2019/6/12 15:23
 * @Version 1.0
 *
 * 由于引用类型的存在，有深克隆和浅克隆之分，若克隆对象中存在引用类型的属性，深克隆会将此属性完全拷贝一份，而浅克隆仅仅是拷贝一份此属性的引用。
 *
 *      我们知道实现Cloneable接口后克隆的对象是浅克隆，要想实现深克隆，请使用:ObjectUtil.cloneByStream(obj)
 *      序列化后拷贝流的方式克隆
 *      对象必须实现Serializable接口
 */
public class DemoHuToolDeepClone implements Serializable {

    /**
     * @Author: umizhang
     * @Description TODO
     * @Date: 2019/6/12 16:48
     * 深克隆：
     *      只会拷贝一份引用；拷贝前后属性不变
     */
    private Integer id;
    /**
     * @Author: umizhang
     * @Description TODO
     * @Date: 2019/6/12 16:48
     * 深克隆：
     *      属性是String的情况，String也是一个类，那String引用类型吗？String的表现有的像基本类型，归根到底就是因为String不可改变，
     *      克隆之后俩个引用指向同一个String，但当修改其中的一个，改的不是String的值，却是新生成一个字符串，让被修改的引用指向新的字符串。
     *      外表看起来就像基本类型一样。；拷贝前后属性不变
     */
    private String text;
    /**
     * @Author: umizhang
     * @Description TODO
     * @Date: 2019/6/12 16:48
     * 深克隆：
     *      引用类型的属性，深克隆会将此属性完全拷贝一份；拷贝前后属性不同
     */
    private DemoDeepClone demoDeepClone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DemoDeepClone getDemoDeepClone() {
        return demoDeepClone;
    }

    public void setDemoDeepClone(DemoDeepClone demoDeepClone) {
        this.demoDeepClone = demoDeepClone;
    }

    @Override
    public String toString() {
        return "DemoHuToolDeepClone{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", demoDeepClone=" + demoDeepClone +
                '}';
    }
}
