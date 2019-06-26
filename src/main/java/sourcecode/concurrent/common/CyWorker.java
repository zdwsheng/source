package sourcecode.concurrent.common;

import java.util.concurrent.CyclicBarrier;

/**
 * @name
 * @ClassName CyWorker
 * @Description 栏栅工人
 * @author: zdw
 * @date 2019/3/12 16:48
 */
public class CyWorker implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private String name;

    public CyWorker(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "正在打桩，毕竟不轻松。。。。。");
        try {
            Thread.sleep(5000);
            System.out.println(name + "不容易，终于把桩打完了。。。。");
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(name + "：等其他逗b把桩都打完了，又得忙活了。。。");
    }
}
