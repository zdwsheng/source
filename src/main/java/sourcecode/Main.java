package sourcecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 张灯皖
 * @name:
 * @desc: TODO
 * @jdk
 * @group
 * @os
 * @date 2018/11/9
 */
public class Main {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = "Mon Dec 10 18:43:43 CST 2018";
        SimpleDateFormat sfEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sfStart = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        System.out.println(new Date().toString());
        System.out.println(sfEnd.format(sfStart.parse(date)));

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int b = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        return b;
    }
}
