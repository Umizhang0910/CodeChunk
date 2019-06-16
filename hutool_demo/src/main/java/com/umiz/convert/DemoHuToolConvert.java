package com.umiz.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.CharsetUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DemoHuToolConvert
 * @Description TODO
 * @Author umizhang
 * @Date 2019/6/12 17:20
 * @Version 1.0
 *
 *      痛点:
 *          在Java开发中我们要面对各种各样的类型转换问题，尤其是从命令行获取的用户参数、从HttpRequest获取的Parameter等等。
 *          这些参数类型多种多样，我们怎么去转换他们呢？常用的办法是先整成String，然后调用XXX.parseXXX方法，还要承受转换失败的风险，不得不加一层try catch，
 *          这个小小的过程混迹在业务代码中会显得非常难看和臃肿。
 *      解决：Convert类
 *          Convert类可以说是一个工具方法类，里面封装了针对Java常见类型的转换，用于简化类型转换。
 *          Convert类中大部分方法为toXXX，参数为Object，可以实现将任意可能的类型转换为指定类型。
 *          同时支持第二个参数defaultValue用于在转换失败时返回一个默认值。
 */
public class DemoHuToolConvert {

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  Java常见类型转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     */
    /*public static void main(String[] args) {
        // 1、转换为字符串：
        int a = 1;
        String aStr = Convert.toStr(a);
        // aStr为："1";

        long[] b = {1,2,3,4,5};
        String bStr = Convert.toStr(b);
        // bStr为："[1, 2, 3, 4, 5]"

        String cStr = Convert.toStr(null, "default");
        // cStr为："default";

        System.out.println(aStr+"\n");
        System.out.println(bStr+"\n");
        System.out.println(cStr+"\n");


        // 2、转换为指定类型数组：
        String[] aArr = { "1", "2", "3", "4" };
        Integer[] intArray = Convert.toIntArray(aArr);

        long[] bArr = {1,2,3,4,5};
        Integer[] intArray2 = Convert.toIntArray(bArr);


        // 3、转换为日期对象：
        String aDate = "2017-05-06";
        Date ad = Convert.toDate(aDate);

        String bDate = "2017-05-06日期";
        Date bd = Convert.toDate(bDate, new Date());

        System.out.println(ad);
        System.out.println(bd);


        // 4、转换为集合
        Object[] aList = {"a", "你", "好", "", 1};
        List<?> al = Convert.toList(aList);

        // 从4.1.11之前可以这么用
        List<?> list = Convert.convert(List.class, aList);

    }*/

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  其他类型转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     */
    /*public static void main(String[] args) {
        // 通过Convert.convert(Class<T>, Object)方法可以将任意类型转换为指定类型，Hutool中预定义了许多类型转换，例如转换为URI、URL、Calendar等等，这些类型的转换都依托于ConverterRegistry类。
        // 通过这个类和Converter接口，我们可以自定义一些类型转换。详细的使用请参阅“自定义类型转换”一节。

    }*/

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  半角和全角转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     *      在很多文本的统一化中这两个方法非常有用，主要对标点符号的全角半角转换。
     */
    /*public static void main(String[] args) {
        // 半角转全角：
        String a = "1234567890";
        String sbc = Convert.toSBC(a);
        System.out.println(sbc);
        // sbc为："１２３４５６７８９０"

        // 全角转半角：
        String b = "１２３４５６７８９０";
        String dbc = Convert.toDBC(b);
        System.out.println(dbc);
        // dbc为："1234567890"
    } */

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  16进制（Hex）
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      在很多加密解密，以及中文字符串传输（比如表单提交）的时候，会用到16进制转换，就是Hex转换，
     *      为此Hutool中专门封装了HexUtil工具类，考虑到16进制转换也是转换的一部分，因此将其方法也放在Convert类中。
     *
     *      说明：
     *      因为字符串牵涉到编码问题，因此必须传入编码对象，此处使用UTF-8编码。
     *      toHex方法同样支持传入byte[]，同样也可以使用hexToBytes方法将16进制转为byte[]。
     */
    /*public static void main(String[] args) {
        // 转为16进制（Hex）字符串：
        String a = "我是一个小小的可爱的字符串";
        String aHex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);

        System.out.println(aHex);
        // hex为："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"

        // 将16进制（Hex）字符串转为普通字符串:
        String bHex = "e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2";
        String b = Convert.hexToStr(bHex, CharsetUtil.CHARSET_UTF_8);

        System.out.println(b);
        // b为："我是一个小小的可爱的字符串"
        //注意：在4.1.11之前为hexStrToStr
        String raw = Convert.hexStrToStr(bHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(raw);
    } */

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  Unicode和字符串转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      与16进制类似，Convert类同样可以在字符串和Unicode之间轻松转换：
     *
     *      很熟悉吧？如果你在properties文件中写过中文，你会明白这个方法的重要性。
     */
    /*public static void main(String[] args) {
        String a = "我是一个小小的可爱的字符串";

        // 转换为Unicode
        String unicode = Convert.strToUnicode(a);
        System.out.println(unicode);
        // 结果为："\u6211\u662f\u4e00\u4e2a\u5c0f\u5c0f\u7684\u53ef\u7231\u7684\u5b57\u7b26\u4e32"

        // 将Unicode字符串转为普通字符串:
        String b = Convert.unicodeToStr(unicode);
        System.out.println(b);
        // b为："我是一个小小的可爱的字符串"
    }  */

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  编码转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      在接收表单的时候，我们常常被中文乱码所困扰，其实大多数原因是使用了不正确的编码方式解码了数据。
     *      于是Convert.convertCharset方法便派上用场了，它可以把乱码转为正确的编码方式：
     * 注意：
     *      注意 经过测试，UTF-8编码后用GBK解码再用GBK编码后用UTF-8解码会存在某些中文转换失败的问题。
     */
    /*public static void main(String[] args) {
        String a = "我不是乱码";

        String res = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        System.out.println(res);
        // 结果为："æä¸æ¯ä¹±ç "

        String str = Convert.convertCharset(res, CharsetUtil.ISO_8859_1, "UTF-8");
        System.out.println(str);
        // 结果为："我不是乱码"
    }*/

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  时间单位转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      Convert.convertTime方法主要用于转换时长单位，比如一个很大的毫秒，我想获得这个毫秒数对应多少分：
     */

    /*public static void main(String[] args) {
        long a = 4535345;

        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(minutes);
        // 结果为："75"  分钟
    } */

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  金额大小写转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      面对财务类需求，Convert.digitToChinese将金钱数转换为大写形式：
     * 注意：
     *      注意 转换为大写只能精确到分（小数点儿后两位），之后的数字会被忽略。
     */
    /*public static void main(String[] args) {
        double a = 67556.32;

        String chinese = Convert.digitToChinese(a);
        System.out.println(chinese);
        // 结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
    }*/

    /**
     * @Author: umizhang
     * @Title: main
     * @Description TODO  原始类和包装类转换
     * @Date: 2019/6/12 17:24
     * @Param:
     * @return:
     * @Exception:
     *
     * 说明：
     *      有的时候，我们需要将包装类和原始类相互转换（比如Integer.classs 和 int.class），这时候我们可以：
     */
    public static void main(String[] args) {

        // 去包装
        Class<?> integerClass = Integer.class;
        Class<?> unWrap = Convert.unWrap(integerClass);
        //结果为：int.class

        // 包装
        Class<?> longClass = long.class;
        Class<?> wrap = Convert.wrap(longClass);
        //结果为：Long.class
    }
}
