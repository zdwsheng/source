package base.concurrent;

/**
 * @author: 张灯皖
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class CurrentSense {
    public static void main(String[] args) {

    }

    //锁的叙述思路
    private void sense() {
        /**
         * 1.锁是悲观还是乐观锁，乐观锁：CAS算法；悲观锁：lock,synchronized
         * 2.线程进入锁的时候，是否为独享锁，还是共享锁，ReentrantLock和synchronized为独享，ReentrantWriteLock为写独享，读共享
         * 3.线程A占有锁的时候，是否为可重入，如果一个线程多次获取锁，锁就升级为偏向锁，那么就会自动获取锁
         *  ps: 自旋锁，轻量级锁和重量级锁是针对synchronized的
         * 4.线程B尝试获取线程A的锁，锁升级为轻量级锁，然后B进行自旋的形式获取锁，不会阻塞
         * 5.如果一段时间没有获取锁，那么锁升级为重量级锁，线程B阻塞，别的线程尝试获取锁也会直接阻塞
         * 6.释放锁的时候，如果是公平锁，那么就按照队列来，先排队的先获取，如果为非公平锁，可以提高吞吐量
         */
    }
}
