package base.concurrent.common;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @name
 * @ClassName Worker
 * @Description 工人
 * @author: zdw
 * @date 2019/3/12 16:02
 */
public class Worker implements Runnable {
    private CountDownLatch countDownLatch;
    private String name;

    public Worker(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    @Override
    public void run() {
        this.doWork();
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //计数器减一，代表已经有一个(线程)事情发生
        this.countDownLatch.countDown();
        this.workOver();
    }

    private void doWork() {
        System.out.println(this.name + "开始工作...........");
    }

    private void workOver() {
        System.out.println(this.name + "工作完成.............");

    }
}
