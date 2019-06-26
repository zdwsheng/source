package sourcecode.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 张灯皖
 * @name
 * @desc TODO
 * @jdk
 * @group
 * @os
 * @date 2018/11/4
 */
public class MyHashMap {
    private static final MyHashMap instance = new MyHashMap();

    public static void main(String[] args) {
        instance.functions();
    }

    private void hashTable() {
        Hashtable hashtable = new Hashtable();
    }

    /**
     * public class HashMap<K,V> extends AbstractMap<K,V>
     * implements Map<K,V>, Cloneable, Serializable {
     * <p>
     * 公共属性:
     * //默认数组大小，必须为二的倍数
     * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
     * <p>
     * 数组最大值
     * static final int MAXIMUM_CAPACITY = 1 << 30;
     * <p>
     * 负载因子
     * static final float DEFAULT_LOAD_FACTOR = 0.75f;
     * <p>
     * 转换为红黑树的最小值,大于等于这个值
     * static final int TREEIFY_THRESHOLD = 8;
     * <p>
     * 从红黑树转换为链表的最大值，小于这个值，红黑树就换重新转换为链表
     * static final int UNTREEIFY_THRESHOLD = 6;
     * <p>
     * 转换红黑树的另外一个条件，同时数组长度也要大于64
     * static final int MIN_TREEIFY_CAPACITY = 64;
     * <p>
     * 和Node内部类有关的属性：
     * 底层数组
     * transient Node<K,V>[] table;
     * <p>
     * transient Set<Map.Entry<K,V>> entrySet;
     * <p>
     * 实际对象个数
     * transient int size;
     * <p>
     * 操作次数：
     * transient int modCount;
     * <p>
     * 底层数组长度，为计算之后的值：
     * int threshold;
     * <p>
     * 负载因子：默认为0.75f
     * final float loadFactor;
     * <p>
     * 底层数组类型为NODE
     * static class Node<K,V> implements Map.Entry<K,V> {
     * final int hash;
     * final K key;
     * V value;
     * Node<K,V> next;
     * <p>
     * Node(int hash, K key, V value, Node<K,V> next) {
     * <p>
     * }
     * <p>
     * public final K getKey()        { return key; }
     * public final V getValue()      { return value; }
     * public final String toString() { return key + "=" + value; }
     * <p>
     * public final int hashCode() {
     * <p>
     * }
     * <p>
     * public final V setValue(V newValue) {
     * <p>
     * }
     * <p>
     * public final boolean equals(Object o) {
     * <p>
     * }
     * }
     */
    private void hashMap() {
        HashMap hashMap = new HashMap();

        /**
         * https://blog.csdn.net/huzhigenlaohu/article/details/51802457
         *  static final int tableSizeFor(int cap) {
         *        **先减一是为了防止本身就是2的倍数，比如16（100000），不减一就会变成（11111）+1,为32
         *        如果为10(1010)，则运算过后变成（1111）+1为16，总之，最核心的是为了让入参变成大于等于自己的2^n
         *         int n = cap - 1;
         *         n |= n >>> 1;
         *         n |= n >>> 2;
         *         n |= n >>> 4;
         *         n |= n >>> 8;
         *         n |= n >>> 16;        最高位以后都变成1，则等比数列求和为2^n-1.，所以最后要加1
         *         return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
         *     }
         */
        //初始长度会经历一系列的运算，把最高位以后的都变成1，所以为16
        hashMap = new HashMap(9);
        hashMap = new HashMap(9, 0.75f);
        hashMap = new HashMap(new LinkedHashMap());
        //map中的hash值的总和
        hashMap.hashCode();

        /**
         * put的hash值算法
         * static final int hash(Object key) {
         *         int h;
         *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
         *     }
         *
         *     数组中具体hash位置计算:  tab[i = (n - 1) & hash]  ,  n 为数组长度，为2的倍数
         *
         *    put步骤：1.判断数组是否为空(第一次)，
         *              if：1.1若为空，进行扩容
         *             2.判断数组hash位置是否为空，
         *                if:2.1若为空，直接new一个Node节点到p=tab[i = (n - 1) & hash]
         *                else:2.2 若不为空
         *                         if:2.2.1 判断p位置上的key和value是否和正在put的相等,若相等e=p
         *                         else if:为红黑树类型，则putTreeVal
         *                         else:遍历数组:目的是找到为空的节点，然后判断是否要转成红黑树，或者找到key相等的node节点
         *                             for (int binCount = 0; ; ++binCount) {
         *                                 2.2.1.1:if:e=p.next为null,则p.next = newNode(hash, key, value, null);
         *                                         if:Node链表长度大于等于7，转换成红黑树treeifyBin(tab, hash);break;
         *                                 2.2.1.2:if:找到相等的Node节点了，break;
         *                                 p = e;
         *                                 }
         *                          if:2.2.2 ：e!=null，判断是否要把旧值替换成新值,然后return 旧值
         *                          判断是否需要扩容 if (++size > threshold){resize();} return null;
         *
         *
         */
        hashMap.put("hashKey", "hashValue");
        hashMap.putAll(new HashMap());
        //如果值存在了，就不会进行覆盖
        hashMap.putIfAbsent("key", "putIfAbsent");


        /**
         * 跟根据key的hash值和key，先判断table的hash位置第一个是不是要get的，
         * 如果是，直接返回，否则判断first是否为树，如果为树，则调用getTreeNode(hash, key);
         * 要是不为树，则进行遍历，找到key节点，然后返回，否则return null
         */
        hashMap.get("hashKey");
        //如果没找到，返回一个默认值
        hashMap.getOrDefault("hashKey", "defaultValue");

        hashMap.remove("remove");
        hashMap.remove("remove", "rm");

        //值的集合
        Collection values = hashMap.values();
        //key的集合
        Set keySet = hashMap.keySet();
        //Entry数组的集合，next为Entry类型的对象,然后根据key获得value
        Set entrySet = hashMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            //******************************************
            Map.Entry entryTemp = (Map.Entry) iterator.next();
            Object key = entryTemp.getKey();
            Object value = hashMap.get(key);
        }


        hashMap.size();
        hashMap.containsKey("containKey");
        hashMap.containsValue("containValue");

        //遍历并且进行操作
        hashMap.forEach((key, value) -> System.out.println(key + "~~~~~" + value));

    }

    private void linkedHashMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
    }

    private void treeMap() {
        TreeMap treeMap = new TreeMap();
    }


    //线程安全的map
    private void concurrent() {
        ConcurrentHashMap map = new ConcurrentHashMap();
    }

    private void functions() {
        Map map = new HashMap();
        map.put("1", "2");
        map.putIfAbsent("1", "如果key:[1] 不存在就put,存在了就不put");
        System.out.println(map.get("1"));
        /**
         * compute计算入参二表达式的值V，如果为空就删除key，如果不为空，就设置key的值为V,并且返回新的值
         */
        Object newValue = map.compute("1", (a, b) -> !a.equals(b));
//        Object newValue = map.compute("1", (a, b) -> null);
        System.out.println(newValue);
        System.out.println(map.get("1"));
    }
}
