package sourcecode.concurrent.lockAndAQS;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 张灯皖
 * @name lock锁, synchronized
 * @desc https://mp.weixin.qq.com/s?__biz=MzU4NDQ4MzU5OA==&mid=2247484539&idx=1&sn=3500cdcd5188bdc253fb19a1bfa805e6&chksm=fd98521acaefdb0c5167247a1fa903a1a53bb4e050b558da574f894f9feda5378ec9d0fa1ac7&token=1604028915&lang=zh_CN#rd
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
/**
 * 相比synchronized，ReenTrantLock增加了一些高级功能。主要来说主要有三点：①等待可中断；
 * ②可实现公平锁；
 * ③可实现选择性通知（锁可以绑定多个条件）
 * <p>
 * ReenTrantLock提供了一种能够中断等待锁的线程的机制，通过lock.lockInterruptibly()来实现这个机制。
 * 也就是说正在等待的线程可以选择放弃等待，改为处理其他事情。
 * <p>
 * ReenTrantLock可以指定是公平锁还是非公平锁。而synchronized只能是非公平锁。
 * 所谓的公平锁就是先等待的线程先获得锁。 ReenTrantLock默认情况是非公平的，
 * 可以通过 ReenTrantLock类的ReentrantLock(boolean fair)构造方法来制定是否是公平的。
 * <p>
 * synchronized关键字与wait()和notify/notifyAll()方法相结合可以实现等待/通知机制，
 * ReentrantLock类当然也可以实现，但是需要借助于Condition接口与newCondition() 方法。
 * Condition是JDK1.5之后才有的，它具有很好的灵活性，比如可以实现多路通知功能也就是在一个Lock对象中
 * 可以创建多个Condition实例（即对象监视器），线程对象可以注册在指定的Condition中，
 * 从而可以有选择性的进行线程通知，在调度线程上更加灵活。 在使用notify/notifyAll()方法进行通知时，
 * 被通知的线程是由 JVM 选择的，用ReentrantLock类结合Condition实例可以实现“选择性通知” ，
 * 这个功能非常重要，而且是Condition接口默认提供的。而synchronized关键字就相当于整个Lock对象中只有一个Condition实例，
 * 所有的线程都注册在它一个身上。如果执行notifyAll()方法的话就会通知所有处于等待状态的线程这样会造成很大的效率问题，
 * 而Condition实例的signalAll()方法 只会唤醒注册在该Condition实例中的所有等待线程。
 */

/**
 *   final void lock() {
 *             if (compareAndSetState(0, 1))
 *                 setExclusiveOwnerThread(Thread.currentThread());
 *             else
 *                 acquire(1);
 *         }
 * lock，每次需要访问资源的时候，会判断，state状态是否为0，
 * 如果为0，则说明没有线程持有，设置状态值为1，把访问资源线程设置成占用线程
 * 否则，acquire(1)，
 * public final void acquire(int arg) {
 *         if (!tryAcquire(arg) &&
 *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 *             selfInterrupt();
 *     }
 *     protected final boolean tryAcquire(int acquires) {
 *             return nonfairTryAcquire(acquires);
 *         }
 *          final boolean nonfairTryAcquire(int acquires) {
 *             final Thread current = Thread.currentThread();
 *             int c = getState();
 *             if (c == 0) {
 *                 if (compareAndSetState(0, acquires)) {
 *                     setExclusiveOwnerThread(current);
 *                     return true;
 *                 }
 *             }
 *             else if (current == getExclusiveOwnerThread()) {
 *                 int nextc = c + acquires;
 *                 if (nextc < 0) // overflow
 *                     throw new Error("Maximum lock count exceeded");
 *                 setState(nextc);
 *                 return true;
 *             }
 *             return false;
 *         }
 *  首先判断state是否为0，如果是，则持有，否则判断，是否为重入锁，如果是，state加上acquires，
 *  否则，返回false
 *
 * 释放锁:
 * protected final boolean tryRelease(int releases) {
 *             int c = getState() - releases;
 *             if (Thread.currentThread() != getExclusiveOwnerThread())
 *                 throw new IllegalMonitorStateException();
 *             boolean free = false;
 *             if (c == 0) {
 *                 free = true;
 *                 setExclusiveOwnerThread(null);
 *             }
 *             setState(c);
 *             return free;
 *         }
 *  将状态值state减去相应值，然后判断是否为0，如果为0，
 *  说明没有线程占用了，然后设置占用线程为null，设置state
 *  返回空闲，否则，返回不空闲
 */
public class MyLock {
    private volatile static MyLock myLocal = null;

    private MyLock() {

    }

    public static MyLock getInstance() {
        if (myLocal == null) {
            synchronized (MyLock.class) {
                if (myLocal == null) {
                    myLocal = new MyLock();
                }
            }
        }
        return myLocal;
    }

    public static void main(String[] args) {

    }

    private void lockTest() throws InterruptedException {
        Lock lock = new ReentrantLock();
        //中断等待
        lock.lockInterruptibly();
        //是否指定为公平锁
        lock = new ReentrantLock(true);
        lock.lock();//相当于Object 的 wait
        System.out.println("lock is lock");
        //相当于Object 的 notify,不过lock可以在任意地方await和signal，而wait()和notify智能在synchronized代码块中使用
        lock.unlock();
        System.out.println("lock is un lock");
        this.lockCon(lock);
    }

    private void lockCon(Lock lock) {
        Condition condition = lock.newCondition();
        try {
            condition.await();
            System.out.println("condition is await");
            //Condition实例的signalAll()方法 只会唤醒注册在该Condition实例中的所有等待线程
            condition.signal();
            System.out.println(" condition is signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sy() {
        synchronized (this) {
            try {
                this.wait();
                System.out.println("synchronized await");
                this.notify();
                System.out.println("synchronized notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * final void lock() {
     *             if (compareAndSetState(0, 1))
     *                 setExclusiveOwnerThread(Thread.currentThread());
     *             else
     *                 acquire(1);
     *         }
     *  protected final boolean tryAcquire(int acquires) {
     *             return nonfairTryAcquire(acquires);
     *  }
     */
    private void unFairLock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }

    /**
     *  final void lock() {
     *             acquire(1);
     *         }
     *    protected final boolean tryAcquire(int acquires) {
     *             final Thread current = Thread.currentThread();
     *             int c = getState();
     *             if (c == 0) {
     *                 if (!hasQueuedPredecessors() &&
     *                     compareAndSetState(0, acquires)) {
     *                     setExclusiveOwnerThread(current);
     *                     return true;
     *                 }
     *             }
     *             else if (current == getExclusiveOwnerThread()) {
     *                 int nextc = c + acquires;
     *                 if (nextc < 0)
     *                     throw new Error("Maximum lock count exceeded");
     *                 setState(nextc);
     *                 return true;
     *             }
     *             return false;
     *         }
     */
    private void fairLock() {

    }
}
