package base.concurrent.synchronize;


import base.concurrent.executor.MyThread;

/**
 * @author: 张灯皖
 * @name synchronize的几种用法  synchronize能加在哪些地方？什么区别？
 * @desc 1.加载静态方法上
 * 2.加载普通方法上
 * 3.加载方法代码块中，静态方法参数为Class对象，普通方法为实力对象引用this
 * 总结:
 * A. 无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；
 * 如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。 
 * B. 每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。 
 * C. 实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
 * ---------------------
 * 原文：https://blog.csdn.net/sinat_32588261/article/details/72880159
 * @jdk
 * @group
 * @os
 * @date 2018/11/4
 */
public class SynchronizedDemo {
    private static SynchronizedDemo sychroizeDemo;

    private SynchronizedDemo() {

    }

    //concurrent.synchronize 加在静态代码块上,线程安全单例模式
    public static SynchronizedDemo getInstance() {
        if (sychroizeDemo == null) {
            synchronized (SynchronizedDemo.class) {
                if (sychroizeDemo == null) {
                    sychroizeDemo = new SynchronizedDemo();
                }
            }
        }
        return sychroizeDemo;
    }

    public static void main(String[] args) {
        System.out.println("?");
        SynchronizedDemo.getInstance().test();
    }

    private synchronized void syThis() {
            System.out.println("synchronized 代码块参数为this,不能为静态方法");
    }

    private synchronized void syCommon() {

    }

    private synchronized static void syStatic() {

    }

    int count = 0;

    private void test() {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread,"thread1");
        thread.start();
        Thread thread2 = new Thread(myThread,"thread2");
        thread2.start();
        Thread thread3 = new Thread(myThread,"thread3");
        thread3.start();
        Thread thread4 = new Thread(myThread,"thread4");
        thread4.start();
        Thread thread5 = new Thread(myThread,"thread5");
        thread5.start();
    }

}
