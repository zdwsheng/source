package base.concurrent.lockAndAQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockAndCondition
 * @Description lock锁和 condition 交替打印ABC
 * @author: zdw
 * @date 2019/9/16 10:08
 */
public class LockAndCondition {

    private static final Lock lock = new ReentrantLock();
    private volatile static int flag = 1;
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new NumALockCondition()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new NumBLockCondition()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new NumCLockCondition()).start();
    }

    private static class NumALockCondition implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {

                    if (flag++ % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": A");
                    }
                    TimeUnit.SECONDS.sleep(1);
                    conditionB.signal();
                    conditionA.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class NumBLockCondition implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {

                    if (flag++ % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + ": B");
                    }
                    TimeUnit.SECONDS.sleep(1);
                    conditionC.signal();
                    conditionB.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class NumCLockCondition implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (true) {
                    if (flag++ % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": C");
                    }
                    TimeUnit.SECONDS.sleep(1);
                    conditionA.signal();
                    conditionC.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
