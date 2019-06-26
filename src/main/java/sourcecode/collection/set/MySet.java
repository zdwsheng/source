package sourcecode.collection.set;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: 张灯皖
 * @name
 * @desc TODO
 * @jdk
 * @group
 * @os
 * @date 2018/11/4
 */
public class MySet {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        TreeSet treeSet = new TreeSet();

        //线程安全的set
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    }

}
