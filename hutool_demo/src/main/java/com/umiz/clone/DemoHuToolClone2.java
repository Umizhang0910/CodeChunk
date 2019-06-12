package com.umiz.clone;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;

/**
 * @ClassName DemoHuToolClone
 * @Description TODO 继承泛型克隆类
 * @Author umizhang
 * @Date 2019/6/12 14:58
 * @Version 1.0
 *      解决问题 :
 *      1、实现此接口cn.hutool.core.clone.Cloneable依旧有不方便之处，就是必须自己实现一个public类型的clone()方法，还要调用父类（Object）的clone方法并处理异常
 *      2、于是cn.hutool.clone.CloneSupport类产生，这个类帮我们实现了上面的clone方法，因此只要继承此类，不用写任何代码即可使用clone()方法。
 *
 *      不足：
 *      当然，使用CloneSupport的前提是你没有继承任何的类，谁让Java不支持多重继承呢（你依旧可以让父类实继承这个类，如果可以的话）。
 *      如果没办法继承类，那实现cn.hutool.clone.Cloneable也是不错的主意，因此hutool提供了这两种方式，任选其一，在便捷和灵活上都提供了支持。
 */
public class DemoHuToolClone2 extends CloneSupport<DemoHuToolClone2> {

    private Integer id;

    private String text;


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
        DemoHuToolClone2 demoJdkClone = (DemoHuToolClone2) obj;

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
        return "DemoHuToolClone2{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
