package com.umiz.clone;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.Cloneable;

/**
 * @ClassName DemoHuToolClone
 * @Description TODO 实现泛型克隆接口
 * @Author umizhang
 * @Date 2019/6/12 14:58
 * @Version 1.0
 *      解决问题 :
 *      1、JDK中的Cloneable接口只是一个空接口，并没有定义成员，它存在的意义仅仅是指明一个类的实例化对象支持位复制（就是对象克隆），
 *      如果不实现这个类，调用对象的clone()方法就会抛出CloneNotSupportedException异常。
 *      2、因为JDK中clone()方法在Object对象中，返回值也是Object对象，因此克隆后我们需要自己强转下类型。
 */
public class DemoHuToolClone implements Cloneable<DemoHuToolClone> {

    private Integer id;

    private String text;


    @Override
    public DemoHuToolClone clone() {
        try {
            return (DemoHuToolClone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new CloneRuntimeException(e);
        }
    }

    /**
     * @Author: umizhang
     * @Title: equals
     * @Description TODO 重写equals方法
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return  true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DemoHuToolClone demoJdkClone = (DemoHuToolClone) obj;

        if (!id.equals(demoJdkClone.getId())) {
            return false;
        }
        return text.equals(demoJdkClone.getText());
    }

    /**
     * @Author: umizhang
     * @Title: equals
     * @Description TODO 重写hashCode方法
     */
    @Override
    public int hashCode() {
        int res = text.hashCode();
        res = 31 * res + id;
        return res;
    }

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

    @Override
    public String toString() {
        return "DemoHuToolClone{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
