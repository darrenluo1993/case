package pers.darren.charset;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ChineseByteConversion {

    public static void main(String[] args) {
        try {
            System.out.println("Default>>>" + Arrays.toString("羅".getBytes()));
            System.out.println("UTF8>>>" + Arrays.toString("羅".getBytes("UTF8"))); // 国际标准
            System.out.println("UTF16>>>" + Arrays.toString("羅".getBytes("UTF16"))); // 国际标准
            System.out.println("UTF32>>>" + Arrays.toString("羅".getBytes("UTF32"))); // 国际标准
            System.out.println("GBK>>>" + Arrays.toString("羅".getBytes("GBK"))); // 简体与繁体
            System.out.println("BIG5>>>" + Arrays.toString("羅".getBytes("BIG5"))); // 繁体中文
            System.out.println("GB2312>>>" + Arrays.toString("罗".getBytes("GB2312"))); // 简体中文
            System.out.println("GB18030>>>" + Arrays.toString("羅".getBytes("GB18030"))); // 简体与繁体
            System.out.println();
            System.out.println("Default>>>" + new String("羅".getBytes()));
            System.out.println("UTF8>>>" + new String("羅".getBytes("UTF8"), "UTF8"));
            System.out.println("UTF16>>>" + new String("羅".getBytes("UTF16"), "UTF16"));
            System.out.println("UTF32>>>" + new String("羅".getBytes("UTF32"), "UTF32"));
            System.out.println("GBK>>>" + new String("羅".getBytes("GBK"), "GBK"));
            System.out.println("BIG5>>>" + new String("羅".getBytes("BIG5"), "BIG5"));
            System.out.println("GB2312>>>" + new String("罗".getBytes("GB2312"), "GB2312"));
            System.out.println("GB18030>>>" + new String("羅".getBytes("GB18030"), "GB18030"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
