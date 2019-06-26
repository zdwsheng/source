package sourcecode.concurrent.executor;

/**
 * @author: 张灯皖
 * @name
 * @desc Thread其实也是实现了Runnable
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("hello world ,this is my extends thread");
    }
}
