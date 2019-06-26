package sourcecode.concurrent.automicTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: 张灯皖
 * @name: java原子操作包下面的类测试
 * @desc: https://blog.csdn.net/mmoren/article/details/79185862
 * @jdk
 * @group
 * @os
 * @date 2019/1/2
 */
public class Demo {
    AtomicInteger atomicInteger = new AtomicInteger(5);
    private static Demo demo = new Demo();

    public static void main(String[] args) {
        demo.add();
    }

    private void add() {
        boolean b = this.atomicInteger.compareAndSet(7, 7);
        System.out.println(b);
    }

    private void addAndGet() {
        int i = this.atomicInteger.addAndGet(7);
        System.out.println("addAndGet:" + i);
    }
    private void atomicStampedReference(){
        /**
         * AtomicStampedReference原子类是一个带有时间戳的对象引用，在每次修改后，
         * AtomicStampedReference不仅会设置新值而且还会记录更改的时间。当AtomicStampedReference设置对象值时，
         * 对象值以及时间戳都必须满足期望值才能写入成功，这也就解决了反复读写时，无法预知值是否已被修改的窘境
         */
        AtomicStampedReference stampedReference = new AtomicStampedReference(1,1);
    }
    /**
     * CAS的全称是Compare And Swap 即比较交换，其算法核心思想如下
     * <p>
     * 执行函数：CAS(V,E,N)
     * <p>
     * 其包含3个参数
     * <p>
     * V表示要更新的变量
     * <p>
     * E表示预期值
     * <p>
     * N表示新值、
     *  如果V值等于E值，则将V的值设为N。若V值和E值不同，则说明已经有其他线程做了更
     * ，则当前线程什么都不做。通俗的理解就是CAS操作需要我们提供一个期望值，当期望值与当前线程的变量值相同时，
     * 说明还没线程修改该值，当前线程可以进行修改，也就是执行CAS操作，但如果期望值与当前线程不符，则说明该值已被其他线程修改，
     * 此时不执行更新操作，但可以选择重新读取该变量再尝试再次修改该变量，也可以放弃操作
     */
    private void notes() {
        this.atomicInteger.getAndAdd(5);
        /**
         * public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         do {
         *             //根据预期值和偏移量获得当前的值
         *             var5 = this.getIntVolatile(var1, var2);
         *             如果当前值不和预期值相等，就一直循环，重新读取值，直到相等为止，否则就进行替换
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *
         *         return var5;
         *     }
         *
         //第一个参数o为给定对象，offset为对象内存的偏移量，通过这个偏移量迅速定位字段并设置或获取该字段的值，
         //expected表示期望值，x表示要设置的值，下面3个方法都通过CAS原子指令执行操作。
         public final native boolean compareAndSwapObject(Object o, long offset,Object expected, Object x);

         public final native boolean compareAndSwapInt(Object o, long offset,int expected,int x);

         public final native boolean compareAndSwapLong(Object o, long offset,long expected,long x);
         */
    }

    /**
     * 悲观锁，每次都去内存根据this对象和valueOffset偏移量获得预期值var5，如果获得var5之后，
     * 内存值没有被改变，还是var5，就可以进行操作，否则被别的线程改变了，重新获得预期值，再次进行校验
     */
    public class unSafeMethod {
        /**
         *public final int getAndAdd(int delta) {
         *         return unsafe.getAndAddInt(this, valueOffset, delta);
         *     }
         */
        public final int getAndAddInt(Object var1, long var2, int var4) {
            int var5;
            do {
                //根据当前对象和偏移量获得预期var5，如果var5和内存值（根据var1-this和var2-valueOffset获得）相等，则进行替换
                var5 = this.getIntVolatile(var1, var2);//----预期值
            } while (!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

            return var5;
        }

        public final long getAndAddLong(Object var1, long var2, long var4) {
            long var6;
            do {
                var6 = this.getLongVolatile(var1, var2);
            } while (!this.compareAndSwapLong(var1, var2, var6, var6 + var4));

            return var6;
        }

        public final int getAndSetInt(Object var1, long var2, int var4) {
            int var5;
            do {
                var5 = this.getIntVolatile(var1, var2);
            } while (!this.compareAndSwapInt(var1, var2, var5, var4));

            return var5;
        }

        public final long getAndSetLong(Object var1, long var2, long var4) {
            long var6;
            do {
                var6 = this.getLongVolatile(var1, var2);
            } while (!this.compareAndSwapLong(var1, var2, var6, var4));

            return var6;
        }

        public final Object getAndSetObject(Object var1, long var2, Object var4) {
            Object var5;
            do {
                var5 = this.getObjectVolatile(var1, var2);
            } while (!this.compareAndSwapObject(var1, var2, var5, var4));

            return var5;
        }

        public native long getLongVolatile(Object var1, long var2);

        public final native boolean compareAndSwapObject(Object var1, long var2, Object var4, Object var5);

        public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);

        public final native boolean compareAndSwapLong(Object var1, long var2, long var4, long var6);

        public native Object getObjectVolatile(Object var1, long var2);

        public native int getIntVolatile(Object var1, long var2);
    }
}
