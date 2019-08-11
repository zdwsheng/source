package base.concurrent.concurrentDemo;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 张灯皖
 * @name concurrent包下面的一些类
 * @desc:
 * @jdk
 * @group
 * @os
 * @date 2018/11/5
 */
public class Concurrent {
    public static void main(String[] args) {

    }

    /**
     * https://www.cnblogs.com/lujiango/p/7580558.html 操作详解
     */
    private void test() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("con", "value");
    }
    /**
     * 原子操作
     *   @SuppressWarnings("unchecked")
     *   // 获取tab数组的第i个node<br>
     *     static final <K,V> Node<K,V> tabAt(Node<K,V>[] tab, int i) {
     *         return (Node<K,V>)U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
     *     }
     *
     *
     * // 利用CAS算法设置i位置上的node节点。在CAS中，会比较内存中的值与你指定的这个值是否相等
     *  如果相等才接受你的修改，否则拒绝修改，即这个操作有可能不成功。
     *     static final <K,V> boolean casTabAt(Node<K,V>[] tab, int i,
     *                                         Node<K,V> c, Node<K,V> v) {
     *         return U.compareAndSwapObject(tab, ((long)i << ASHIFT) + ABASE, c, v);
     *     }
     *
     * // 利用volatile方法设置第i个节点的值，这个操作一定是成功的。
     *     static final <K,V> void setTabAt(Node<K,V>[] tab, int i, Node<K,V> v) {
     *         U.putObjectVolatile(tab, ((long)i << ASHIFT) + ABASE, v);
     *     }
     */
}
