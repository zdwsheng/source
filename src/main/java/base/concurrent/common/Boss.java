package base.concurrent.common;

import java.util.concurrent.CountDownLatch;

/**
 * @name
 * @ClassName Boss
 * @Description 老板
 * @author: zdw
 * @date 2019/3/12 16:07
 */
public class Boss implements Runnable {
    private CountDownLatch countDownLatch;

    public Boss(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("老板正在等所有人工作完成");
        try {
            //等待，等计数器为0，等待所有事情做完
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有工人已经完成工作，老板开始检查");
    }
}
