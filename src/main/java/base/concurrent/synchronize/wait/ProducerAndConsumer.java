package base.concurrent.synchronize.wait;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ProducerAndConsumer
 * @Description 生产者和消费者
 * @author: zdw
 * @date 2019/9/24 18:00
 */
public class ProducerAndConsumer {

    /**
     * 队列生产者最大数量
     */
    private static final int MAX_SIZE = 10;

    /**
     * 对象消费者最小数量
     */
    private static final int MIN_SIZE = 0;


    private static int flag = 0;

    private static final Deque<Integer> deque = new ConcurrentLinkedDeque<>();


    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }

    /**
     * 生产者
     */
    private static final class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                producer();
            }
        }

        private void producer() {
            synchronized (deque) {
                try {
                    //生产达到最大数量了
                    while (deque.size() == MAX_SIZE) {
                        System.out.println("生产达到最大数量了..................,数量:" + deque.size());
                        TimeUnit.SECONDS.sleep(1);
                        deque.wait();
                    }
                    deque.offer(++flag);
                    System.out.println("生产了一个产品，通知消费者，队列数量: " + deque.size());
                    TimeUnit.MILLISECONDS.sleep(300);
                    deque.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    private static final class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                consumer();
            }
        }

        private void consumer() {

            synchronized (deque) {
                try {
                    while (deque.size() == MIN_SIZE) {
                        System.out.println("消费队列为空，不能消费了................:" + deque.size());
                        deque.wait();
                    }
                    Integer product = deque.poll();
                    System.out.println("消费了一个..........:" + product + " 剩余消费数量:" + deque.size());
                    TimeUnit.SECONDS.sleep(1);
                    deque.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
