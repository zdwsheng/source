package base.concurrent.executor;

import java.util.concurrent.*;

/**
 * @author: 张灯皖
 * @name
 * @desc 线程池
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class  MyExecutor {
    private static MyExecutor myExecutor = new MyExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        myExecutor.testExecutors();
    }

    private void testExecutors() throws ExecutionException, InterruptedException, TimeoutException {
        this.exeExecute();
        this.exeSubmit();
    }

    private void exeExecute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //execute一般用于执行实现了runnable的类，不需要返回值，无法判断任务是否被线程池执行成功与否
        executorService.execute(new MyThread());
        executorService.execute(new MyRunnableThread());
    }

    private void exeSubmit() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //submit一般用于执行实现了Callable的接口，可以得到返回值，并知道返回结果
        //public class FutureTask<V> implements RunnableFuture<V>
        Future<String> callAbleSubmit = executorService.submit(new MyCallable());
        boolean cancelled = callAbleSubmit.isCancelled();
        boolean done = callAbleSubmit.isDone();
        /**
         * get()方法会阻塞当前线程直到任务完成，
         * 而使用 get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，
         * 这时候有可能任务没有执行完
         */
        String get = callAbleSubmit.get();
//        String getByTime = callAbleSubmit.get(10L, TimeUnit.SECONDS);
        System.out.println("callAble执行 isCancelled:" + cancelled +
                "  isDone:" + done + "  返回值:" + get);
    }

    /**
     * 该方法返回一个固定线程数量的线程池。该线程池中的线程数量始终不变。
     * 当有一个新的任务提交时，线程池中若有空闲线程，则立即执行。若没有，
     * 则新的任务会被暂存在一个任务队列中，待有线程空闲时，便处理在任务队列中的任务。
     * return new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     */
    private void fixExecutors(int count) {
        ExecutorService executorService = Executors.newFixedThreadPool(count);
    }

    /**
     * 方法返回一个只有一个线程的线程池。若多余一个任务被提交到该线程池，
     * 任务会被保存在一个任务队列中，待线程空闲，按先入先出的顺序执行队列中的任务
     * new ThreadPoolExecutor(1, 1,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>()));
     */
    private void singleExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }


    /**
     * CachedThreadPool： 该方法返回一个可根据实际情况调整线程数量的线程池。
     * 线程池的线程数量不确定，但若有空闲线程可以复用，则会优先使用可复用的线程。
     * 若所有线程均在工作，又有新的任务提交，则会创建新的线程处理任务。所有线程在当前任务执行完毕后，
     * 将返回线程池进行复用。
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     */
    private void autoExecutors() {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
