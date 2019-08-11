package base.concurrent.synchronize.threads;

/**
 * @author: 张灯皖
 * @name:
 * @desc:
 * @jdk
 * @group
 * @os
 * @date 2018/11/5
 */
public class MyThread implements Runnable {
    int count;

    @Override
    public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "的 count :" + ++count);
            }
    }
}
