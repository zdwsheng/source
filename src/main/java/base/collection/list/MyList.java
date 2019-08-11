
package base.collection.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: zdw.sheng@foxmail.com
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2018/11/4
 */
public class MyList {
    private static MyList instance = new MyList();

    public static void main(String[] args) {
     /*   Object[] a = {};
        System.out.println(a.length);
        differentBetweenArrayListAndVector();*/
        instance.listIte();
    }

/**
 * List接口的一些抽象方法
 *   public interface List<E> extends Collection<E> {
 *       boolean add(E e);
 *       void add(int index, E element);
 *       boolean addAll(Collection<? extends E> c);
 *       boolean addAll(int index, Collection<? extends E> c);
 *
 *       E set(int index, E element);
 *
 *       E get(int index);
 *
 *       E remove(int index);
 *       boolean remove(Object o);
 *       boolean removeAll(Collection<?> c);
 *
 *
 *       boolean contains(Object o);
 *       boolean containsAll(Collection<?> c);
 *
 *       boolean isEmpty();
 *
 *       Object[] toArray();
 *       <T> T[] toArray(T[] a);
 *
 *       boolean retainAll(Collection<?> c);
 *       boolean equals(Object o);
 *       int hashCode();
 *       int indexOf(Object o);
 *       int lastIndexOf(Object o);
 *       ListIterator<E> listIterator(int index);
 *       List<E> subList(int fromIndex, int toIndex);
 *
 *
 *   数组最大长度，Integer.MAX_VALUE
 *   private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
 * Java数组对象的剖析
 * 数组对象的形状和结构（例如int值数组）类似于标准Java对象的形状和结构 。
 * 主要区别在于数组对象有一个额外的元数据，表示数组的大小。然后，数组对象的元数据包括：
 *     类：指向类信息的指针，描述对象类型。在int字段数组的情况下 ，这是指向int[]类的指针 。
 *     标志：描述对象状态的标志集合，包括对象的哈希码（如果有），以及对象的形状（即，对象是否为数组）。
 *     锁定：对象的同步信息 - 即对象当前是否已同步。
 *     大小：数组的大小。
 *
 *   最大尺寸  2^31 = 2,147,483,648
 * 作为数组，它自己需要8 bytes存储大小  2,147,483,648 所以 2^31 -8 (for storing size ),
 * 所以最大数组大小定义为Integer.MAX_VALUE - 8
 *     }
 */
    /**
     * public class Vector<E>
     * extends AbstractList<E>
     * implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     * 成员变量:
     * <p>
     * protected Object[] elementData;底层存放对象的数组
     * protected int elementCount;     实际有多少个对象
     * protected int capacityIncrement;扩容增量
     */
    private static void vector() {
        /**
         * 无参构造函数初始数组长度为10，指定扩容长度为0
         * this(10);
         * this(initialCapacity, 0);
         *
         * vector扩容判断，每次add，minCapacity = elementCount + 1去和数组长度 elementData.length 作比较，
         * 如果minCapacity - elementData.length > 0 则进行扩容 grow(int minCapacity)
         * int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
         *                                          capacityIncrement : oldCapacity);
         * 如果构造参数指定了扩容长度和初始长度，则每次扩容为初始长度+指定扩容长度capacityIncrement
         * 如果没有指定的话，则每次扩容初始长度的两倍
         */
        Vector vector = new Vector();
        vector = new Vector(15);

        vector = new Vector(13, 15);
        vector = new Vector(new ArrayList());


        vector.add("e");
        //index：index 不能 > elementCount
        vector.add(2, "e");
        vector.addAll(new ArrayList());
        vector.addAll(1, new ArrayList());

        vector.set(2, "e");

        vector.get(1);

        vector.contains("e");
        //只是把对象赋值位空了，底层数组长度还是没变，size()方法返回的是 elementCount
        vector.remove("e");
        vector.remove(1);
        vector.removeAll(new ArrayList<>());

        //求两个集合的交集
        vector.retainAll(new ArrayList<>());
        vector.setSize(10);
    }


    /**
     * public class ArrayList<E> extends AbstractList<E>
     * implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     * 成员变量:
     * private static final int DEFAULT_CAPACITY = 10; 初始默认数组大小
     *
     * <p>
     * public ArrayList(Collection<? extends E> c)，如果c的size位0，则this.elementData = EMPTY_ELEMENTDATA;
     * public ArrayList(int initialCapacity) ，如果initialCapacity = 0，则this.elementData = EMPTY_ELEMENTDATA;
     * </p>
     * private static final Object[] EMPTY_ELEMENTDATA = {};
     *
     *
     * <p>
     * 实例化一个ArrayList时，若是无参构造函数，this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
     * </p>
     * private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
     * <p>
     * ansient Object[] elementData;       底层数组
     * private int size;    实际包含对象元素大小
     * <p>
     * <p>
     * <p>
     * <p>
     * arrayList几乎所有的操作都是基于数组的复制（ System.arraycopy（..））完成的，例如addAll，remove，removeAll等
     * public static <T> T[] copyOf(T[] original, int newLength)
     */
    private static void arrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList(13);
        arrayList = new ArrayList(new Vector());

        /**
         * add的时候，minCapacity = size+1,如果 if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
         *             return Math.max(DEFAULT_CAPACITY, minCapacity);
         *         }  return minCapacity;
         *  if (minCapacity - elementData.length > 0)
         *             grow(minCapacity);
         *
         * private void grow(int minCapacity) {
         *         // overflow-conscious code
         *         int oldCapacity = elementData.length;
         *         int newCapacity = oldCapacity + (oldCapacity >> 1);
         *         if (newCapacity - minCapacity < 0)
         *             newCapacity = minCapacity;
         *         if (newCapacity - MAX_ARRAY_SIZE > 0)
         *             newCapacity = hugeCapacity(minCapacity);
         *         // minCapacity is usually close to size, so this is a win:
         *         elementData = Arrays.copyOf(elementData, newCapacity);
         * }
         * 由此可见，如果实例化的时候没有指定ArrayList数组长度，第一次add的时候进行扩容时，长度为10
         * 扩容大小方式为：int newCapacity = oldCapacity + (oldCapacity >> 1);
         */
        arrayList.add("arrayList");
        arrayList.add(0, "arrayList0");

        arrayList.addAll(new Vector());
        arrayList.addAll(2, new Vector());

        arrayList.set(1, new Vector<>());

        arrayList.get(1);

        arrayList.remove("arrayList0");
        arrayList.remove(1);
        arrayList.removeAll(new LinkedList<>());

        arrayList.contains("arrayList0");

        arrayList.retainAll(new ArrayList<>());

        //动态修建数组长度位实际元素对象长度,内存紧张的时候可以使用
        arrayList.trimToSize();

        arrayList.size();
    }

    private static void differentBetweenArrayListAndVector() {
        /** 1.初始化的时候，如果ArrayList没有指定数组长度，则默认是个空数组，
         第一次add的时候，才会默认扩容为长度为10的Object数组。
         而Vector实例化没有指定数组长度，直接实例化一个长度为10的Object数组
         */
        /** 2.Vector扩容为int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
         capacityIncrement : oldCapacity);
         ArrayList扩容为：int newCapacity = oldCapacity + (oldCapacity >> 1);
         */
        /**
         Vector的方法都是同步方法，为线程安全的List
         */

    }


    /**
     * https://www.cnblogs.com/yakovchang/p/java_linkedlist.html
     * https://blog.csdn.net/huangfan322/article/details/52756441
     * public class LinkedList<E>
     * extends AbstractSequentialList<E>
     * implements List<E>, Deque<E>, Cloneable, java.io.Serializable
     * <p>
     * <p>
     * 从public interface Deque<E> extends Queue<E> 继承的相关接口
     * void addFirst(E e);
     * void addLast(E e);
     * E getFirst()
     * E getLast()
     * E removeFirst();
     * E removeLast();
     * E peek();
     * E element();
     * E poll();
     * E remove();
     * boolean offer(E e);
     * boolean offerFirst(E e);
     * boolean offerLast(E e);
     * E peekFirst();
     * E peekLast();
     * E pollFirst();
     * E pollLast();
     * void push(E e);
     * E pop();
     * boolean removeFirstOccurrence(Object o);
     * boolean removeLastOccurrence(Object o);
     * Iterator<E> descendingIterator();
     * <p>
     * 从public abstract class AbstractSequentialList<E> extends AbstractList<E> 继承的接口 里面的接口其实都是继承AbstractList的
     * public boolean addAll(int index, Collection<? extends E> c)
     * public E get(int index)
     * public E set(int index, E element)
     * public void add(int index, E element)
     * public E remove(int index)
     * public abstract ListIterator<E> listIterator(int index);
     * <p>
     * 从public abstract class AbstractCollection<E> implements Collection<E> 继承的接口
     * public boolean contains(Object o)
     * public abstract int size();
     * public boolean remove(Object o)
     * public boolean addAll(Collection<? extends E> c)
     * public <T> T[] toArray(T[] a)
     * public Object[] toArray()
     * <p>
     * <p>
     * LinkedList都是基于一个节点类操作的
     * private static class Node<E> {
     * E item;
     * Node<E> next;
     * Node<E> prev;
     * <p>
     * Node(Node<E> prev, E element, Node<E> next) {
     * this.item = element;
     * this.next = next;
     * this.prev = prev;
     * }
     * }
     * 核心方法 追加尾部
     * void linkLast(E e) {
     * final Node<E> l = last;
     * final Node<E> newNode = new Node<>(l, e, null);
     * last = newNode;
     * if (l == null)
     * first = newNode;
     * else
     * l.next = newNode;
     * size++;
     * modCount++;
     * }
     * 追加头部
     * private void linkFirst(E e) {
     * final Node<E> f = first;
     * final Node<E> newNode = new Node<>(null, e, f);
     * first = newNode;
     * if (f == null)
     * last = newNode;
     * else
     * f.prev = newNode;
     * size++;
     * modCount++;
     * }
     * <p>
     * linkedList 的全局属性
     * transient int size = 0;   transient Node<E> first;  transient Node<E> last;
     * <p>
     * 队列相关的参考queue包下面的MyQueue
     */
    private static void linkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList = new LinkedList(new ArrayList());

        /**
         * add是linkLast(e);加在链表后面
         */
        linkedList.add("linkedList");
        linkedList.add(3, "linkedList3");
        linkedList.addAll(new LinkedList());
        linkedList.addAll(3, new LinkedList());
        linkedList.addFirst("first");
        linkedList.addLast("last");
        linkedList.set(3, "linkedSetMethod");

        linkedList.get(3);

        //相当于removeFirst();->unlinkFirst(f);
        linkedList.remove();
        //先获得元素的索引，然后删除索引位置的node节点
        linkedList.remove("linkedList3");
        linkedList.removeFirst();
        linkedList.removeLast();

        linkedList.contains("linkedList3");

        linkedList.clear();

        linkedList.indexOf("linkedList3");


        /**
         *  return add(e);-> linkLast(e); return true;
         *  队列先进先出
         */
        linkedList.offer("linkedList3");
        linkedList.offerFirst("linkedList3");
        linkedList.offerLast("linkedList3");
        linkedList.poll();


        /**
         * 栈 先进后出
         */
        //addFirst(e);
        linkedList.push("linkedList3");
        //return removeFirst();
        linkedList.pop();


        //@return the head of this list, or {@code null} if this list is empty
        /**
         * public E peek() {
         *         final Node<E> f = first;
         *         return (f == null) ? null : f.item;
         *     }
         */
        linkedList.peek();
        //同上
        linkedList.peekFirst();
        //last
        linkedList.peekLast();
    }

    //线程安全的List

    /**
     * 核心是基于数组的复制进行操作，然后把更改后的数组赋值给array,对于一些操作，add，remove，set，都加锁了，而get未加锁
     * public class CopyOnWriteArrayList<E>
     * implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     * <p>
     * <p>
     * 成员属性：
     * final transient ReentrantLock lock = new ReentrantLock();
     * private transient volatile Object[] array;
     * <p>
     * 核心方法：
     * final Object[] getArray() {
     * return array;
     * }
     * final void setArray(Object[] a) {
     * array = a;
     * }
     */
    private static void copyOnWriteList() {
        /**
         * public CopyOnWriteArrayList() {
         *         setArray(new Object[0]);
         *     }
         */
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList = new CopyOnWriteArrayList(new ArrayList());
        CopyOnWriteArrayList[] copy = null;
        copyOnWriteArrayList = new CopyOnWriteArrayList(copy);

        copyOnWriteArrayList.add("copy");
        copyOnWriteArrayList.add(1, "copy");
        copyOnWriteArrayList.addAll(new CopyOnWriteArrayList());
        copyOnWriteArrayList.addAll(1, new CopyOnWriteArrayList());

        copyOnWriteArrayList.addIfAbsent("object");
        copyOnWriteArrayList.addAllAbsent(new CopyOnWriteArrayList());

        copyOnWriteArrayList.set(1, "setCopy");

        copyOnWriteArrayList.get(1);

        copyOnWriteArrayList.remove("remove");
        copyOnWriteArrayList.remove(2);
        copyOnWriteArrayList.removeAll(new CopyOnWriteArrayList<>());
        copyOnWriteArrayList.removeIf(son -> son.equals("copy"));
    }


    private void listIte() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        ListIterator<Integer> integerListIterator = integerList.listIterator();
        while (integerListIterator.hasNext()) {
            int next = integerListIterator.next();
            if (next % 2 == 0) {
                integerListIterator.add(1);
            }
        }
        ListIterator<Integer> listIterator = integerList.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex() + ":" + listIterator.next());
        }
    }
}
