package sourcecode.concurrent.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @name
 * @ClassName MyLatchAndCyclicBarrier
 * @Description 闭锁和栏栅 https://blog.csdn.net/lmc_wy/article/details/7866863
 * @author: zdw
 * 区别：闭锁用于所有线程等待一个外部事件的发生；
 * 栅栏则是所有线程相互等待，直到所有线程都到达某一点时才打开栅栏，然后线程可以继续执行。
 * @date 2019/3/12 15:33
 */
public class MyLatchAndCyclicBarrier {
    private static MyLatchAndCyclicBarrier instance = new MyLatchAndCyclicBarrier();

    public static void main(String[] args) {
        instance.latch();
//        instance.cyclicBarrier();
    }

    /**
     * 闭锁：一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
     * 即，一组线程等待某一事件发生，事件没有发生前，所有线程将阻塞等待；而事件发生后，所有线程将开始执行；
     * 闭锁最初处于封闭状态，当事件发生后闭锁将被打开，一旦打开，闭锁将永远处于打开状态。
     * 闭锁CountDownLatch唯一的构造方法CountDownLatch(int count)，当在闭锁上调用countDown()方法时，
     * 闭锁的计数器将减1，当闭锁计数器为0时，闭锁将打开，所有线程将通过闭锁开始执行。
     * <p>
     * <p>
     * ps:线程外部共同等待某一个契机点
     * countDownLatch.countDown();    计数器减一，代表一件事情完成，可能是一个线程完成任务
     * countDownLatch.await(); 等待计数器为0
     */
    private void latch() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Worker workerZ = new Worker(countDownLatch, "张三");
        Worker workerL = new Worker(countDownLatch, "李四");
        Worker workerW = new Worker(countDownLatch, "王五");
        Boss boss = new Boss(countDownLatch);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(boss);
        executorService.execute(workerZ);
        executorService.execute(workerL);
        executorService.execute(workerW);
        executorService.shutdown();
    }

    /**
     * 栅栏：一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点。
     * 利用栅栏，可以使线程相互等待，直到所有线程都到达某一点，然后栅栏将打开，
     * 所有线程将通过栅栏继续执行。CyclicBarrier支持一个可选的 Runnable 参数，
     * 当线程通过栅栏时，runnable对象将被调用。
     * 构造函数CyclicBarrier(int parties, Runnable barrierAction)，
     * 当线程在CyclicBarrier对象上调用await()方法时，栅栏的计数器将增加1，当计数器为parties时，栅栏将打开。
     * ps:一组线程内部相互等待，它能够阻塞一组线程直到某个事件发生
     */
    private void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyWorker workZ = new CyWorker(cyclicBarrier, "张三");
        CyWorker workL = new CyWorker(cyclicBarrier, "李四");
        CyWorker workW = new CyWorker(cyclicBarrier, "王五");
        executorService.execute(workZ);
        executorService.execute(workW);
        executorService.execute(workL);
        executorService.shutdown();
    }
}
