package base.concurrent.executor;

/**
 * @author: 张灯皖
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class MyRunnableThread implements Runnable {
    @Override
    public void run() {
        System.out.println("hello world , this is my imp runnable");
    }
}
