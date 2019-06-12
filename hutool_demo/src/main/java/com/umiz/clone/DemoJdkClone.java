package com.umiz.clone;

/**
 * @ClassName DemoJdkClone
 * @Description TODO
 * @Author umizhang
 * @Date 2019/6/12 14:49
 * @Version 1.0
 */
public class DemoJdkClone implements Cloneable{

    private Integer id;

    private String text;


    public static void main(String[] args) {
        DemoJdkClone source = new DemoJdkClone();
        source.setId(10010);
        source.setText("这里是JDK的克隆文本!");
        Object clone = null;
        try {
            clone = source.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(source.toString());
        System.out.println(clone.toString());

        /*
         * java比较两个对象是否相等，实现：
         *      可以通过重写equals()实现。
         *      1、==比较的是地址，而equals()比较的是对象内容
         *      2、重写equals()方法必须重写hashCode()
         *
         * 外部调用时来比较
         */
        System.out.println(source.equals(clone));

    }

   /* *//**
     * @Author: umizhang
     * @Title: equals
     * @Description TODO 重写equals方法
     *//*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return  true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DemoJdkClone demoJdkClone = (DemoJdkClone) obj;

        if (!id.equals(demoJdkClone.getId())) {
            return false;
        }
        return text.equals(demoJdkClone.getText());
    }
    *//**
     * @Author: umizhang
     * @Title: equals
     * @Description TODO 重写hashCode方法
     *//*
    @Override
    public int hashCode() {
        int res = text.hashCode();
        res = 31 * res + id;
        return res;
    }*/

    public DemoJdkClone(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public DemoJdkClone() {
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
        return "DemoJdkClone{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
