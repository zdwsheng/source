package base;

import java.time.Duration;
import java.time.Instant;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @name
 * @ClassName ThreadAndDequeue
 * @Description 线程和队列消费
 * @author: zdw
 * @date 2019/8/10 16:24
 */
public class ThreadAndDequeue {
    private ThreadAndDequeue() {
    }

    private static final ThreadAndDequeue INSTANCE = new ThreadAndDequeue();

    private final Deque<String> DEQUE = new ConcurrentLinkedDeque();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            INSTANCE.offerItemToQueue("顺序:" + i);
        }
        Consumer consumer = INSTANCE.new Consumer();
        new Thread(consumer).start();
        TimeUnit.SECONDS.sleep(20);
        consumer.stop();
    }

    private void offerItemToQueue(String item) {
        DEQUE.offer(item);
    }

    private void print(String item) {
        System.out.println("item:" + item);
    }

    private class Consumer implements Runnable {
        private volatile boolean stop = false;

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            Instant now = Instant.now();
            while (!stop) {
                String item = DEQUE.poll();
                if (Objects.nonNull(item)) {
                    print(item);
                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Instant end = Instant.now();
            System.out.println("停止咯.....花费时间 s:" + Duration.between(now, end).getSeconds());
        }
    }
}
