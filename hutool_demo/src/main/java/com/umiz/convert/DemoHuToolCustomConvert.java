package com.umiz.convert;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @ClassName DemoHuToolConvert
 * @Description TODO
 * @Author umizhang
 * @Date 2019/6/12 17:20
 * @Version 1.0
 *
 * 自定义类型转换-ConverterRegistry
 *      由来:
 *          Hutool中类型转换最早只是一个工具类，叫做“Conver”，对于每一种类型转换都是用一个静态方法表示，
 *          但是这种方式有一个潜在问题，那就是扩展性不足，这导致Hutool只能满足部分类型转换的需求。
 *
 *      解决：为了解决这些问题，我对Hutool中这个类做了扩展。思想如下：
 *          Converter 类型转换接口，通过实现这个接口，重写convert方法，以实现不同类型的对象转换
 *          ConverterRegistry 类型转换登记中心。将各种类型Convert对象放入登记中心，通过convert方法查找目标类型对应的转换器，
 *          将被转换对象转换之。在此类中，存放着默认转换器和自定义转换器，默认转换器是Hutool中预定义的一些转换器，自定义转换器存放用户自定的转换器。
 *
 *          通过这种方式，实现类灵活的类型转换。使用方式如下：
 *          int a = 3423;
 *          ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
 *          String result = converterRegistry.convert(String.class, a);
 *          Assert.assertEquals("3423", result);
 */
public class DemoHuToolCustomConvert {

    @Test
    public void test(){
        int a = 3423;
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        String result = converterRegistry.convert(String.class, a);
        Assert.assertEquals("3423", result);
    }

    /**
     * @Author: umizhang
     * @Description TODO  // 1、自定义转换器
     * @Date: 2019/6/16 16:41
     */
    public static class CustomConverter implements Converter<String> {

        @Override
        public String convert(Object value, String defaultValue) throws IllegalArgumentException {
            return "Custom:"+value.toString();
        }

    }
    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO 自定义转换
     * @Date: 2019/6/13 10:54
     * @Param: [args]
     * @return: void
     * @Exception:
     *      说明：
     *          Hutool的默认转换有时候并不能满足我们自定义对象的一些需求，这时我们可以使用ConverterRegistry.getInstance().putCustom()方法自定义类型转换。
     *      注意：
     *          convert(Class type, Object value, T defaultValue, boolean isCustomFirst)方法的最后一个参数可以选择转换时优先使用自定义转换器还是默认转换器。
     *          convert(Class type, Object value, T defaultValue)和convert(Class type, Object value)两个重载方法都是使用自定义转换器优先的模式。
     *
     *      ConverterRegistry单例和对象模式
     *      ConverterRegistry提供一个静态方法getInstance()返回全局单例对象，这也是推荐的使用方式，当然如果想在某个限定范围内自定义转换，可以实例化ConverterRegistry对象。
     *
     */
    public static void main(String[] args) {

        // 2、注册转换器
        ConverterRegistry instance = ConverterRegistry.getInstance();
        //此处做为示例自定义String转换，因为Hutool中已经提供String转换，请尽量不要替换
        //替换可能引发关联转换异常（例如覆盖String转换会影响全局）
        instance.putCustom(String.class, CustomConverter.class);

        // 3、执行转换
        int a = 454553;
        String result = instance.convert(String.class, a);
       // Assert.assertEquals("Custom: 454553", result);
    }
}

