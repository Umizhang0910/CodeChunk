package com.umiz.clone;

import java.io.Serializable;

/**
 * @ClassName DemoDeepClone
 * @Description TODO
 * @Author umizhang
 * @Date 2019/6/12 16:41
 * @Version 1.0
 */
public class DemoDeepClone implements Serializable {

    private String val;

    public DemoDeepClone(String val) {
        this.val = val;
    }

    public DemoDeepClone() {
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "DemoDeepClone{" +
                "val='" + val + '\'' +
                '}';
    }
}
