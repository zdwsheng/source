package base.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @name https://segmentfault.com/a/1190000016524796
 * @ClassName MyQueue 底层是数组，LinkedList底层是链表
 * @Description 队列, 堆栈, 双端队列  堆栈操作方法:push(进) pop(出)  单项队列操作方法: offer(进) poll(出) 双向队列操作方法 :offerFirst offerLast pollFirst pollLast
 * 只查看，不操作元素的方法 peek peekFirst peekLast
 * LinkedList中在“List有序集合”这一篇文章中讲过了，从队列和双端队列的角度来看，LinkedList和ArrayDeque的方法声明都是一致的。只不过LinkedList较之于ArrayDeque多实现了List接口，还具有有序集合List的特性。
 * <p>
 * ArrayDeque和LinkedList的比较
 * ArrayDeque和LinkedList都实现了Deque接口，应该用哪一个呢？如果只需要Deque接口，从两端进行操作，一般而言，
 * ArrayDeque效率更高一些，应该被优先使用。不过，如果同时需要根据索引位置进行操作，或者经常需要在中间进行插入和删除，
 * 则应该选LinkedList（注意，这里使用的是List特性，而不是Deque特性了）
 * @author: zdw
 * @date 2019/8/10 15:21
 */
public class MyQueue {
    private MyQueue() {
    }

    private static final MyQueue instance = new MyQueue();

    public static void main(String[] args) {
//        instance.stack();
//        instance.oneWayQueue();
        instance.twoWayQueue();
    }

    /**
     * deque作为堆栈的方法
     */
    private void stack() {
        Deque<String> stack = new ArrayDeque<>(32);
        //入栈，后进先出
        stack.push("入栈1");
        stack.push("入栈2");
        stack.push("入栈3");

        //出栈
        String pop = stack.pop();
        System.out.println("出栈:" + pop);

        //取栈顶元素 入栈2
        String head = stack.peek();
        System.out.println("栈顶:" + head);
        //取栈底元素 入栈1
        String last = stack.peekLast();
        System.out.println("栈底:" + last);

        System.out.println("是否包含‘入栈2’:" + stack.contains("入栈2") + " 栈元素个数:" + stack.size());
    }

    /**
     * 单项队列
     */
    private void oneWayQueue() {
        Deque<String> oneWayQueue = new ArrayDeque<>(32);
        //进队列
        oneWayQueue.offer("进队列1");
        oneWayQueue.offer("进队列2");
        oneWayQueue.offer("进队列3");

        //出队列 进队列1
        String poll = oneWayQueue.poll();
        System.out.println("出队列元素:" + poll);

        String first = oneWayQueue.peekFirst();
        System.out.println("队首元素:" + first);

        String last = oneWayQueue.peekLast();
        System.out.println("队尾元素:" + last);
        System.out.println("单项队列元素个数:" + oneWayQueue.size());
    }

    /**
     * 双端队列
     */
    private void twoWayQueue() {
        Deque<String> twoWayQueue = new ArrayDeque<>(32);
        //队首插入
        twoWayQueue.offerFirst("1");
        twoWayQueue.offerFirst("2");
        //队尾插入
        twoWayQueue.offerLast("3");
        twoWayQueue.offerLast("4");
        //队首元素
        String first = twoWayQueue.peekFirst();
        System.out.println("双端队列 队首元素:" + first);
        //队尾元素
        String last = twoWayQueue.peekLast();
        System.out.println("双端队列队尾元素:" + last);

        System.out.println("是否包含 2:" + twoWayQueue.contains("2") + "元素个数:" + twoWayQueue.size());

        twoWayQueue.pollFirst();
        twoWayQueue.pollLast();
        System.out.println("是否包含 2:" + twoWayQueue.contains("2") + "元素个数:" + twoWayQueue.size());
    }
}
