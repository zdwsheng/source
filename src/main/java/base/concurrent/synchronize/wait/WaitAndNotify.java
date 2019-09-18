package base.concurrent.synchronize.wait;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WaitAndNotify
 * @Description 等待通知
 * @author: zdw
 * @date 2019/9/16 9:39
 */
public class WaitAndNotify {

    /**
     * 线程控制器
     */
    private volatile static int threadFlag = 0;

    private volatile static int flag = 0;

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread one = new Thread(new Wait(lock));
        Thread two = new Thread(new Wait(lock));
        one.start();
        two.start();
    }

    private static class Wait implements Runnable {
        private Object lock;

        public Wait(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + flag++);
                    lock.notify();
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        }
    }
}
