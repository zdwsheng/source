package base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 张灯皖
 * @name:
 * @desc:
 * @jdk
 * @group
 * @os
 * @date 2018/11/9
 */
public class Main {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws ParseException {

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
