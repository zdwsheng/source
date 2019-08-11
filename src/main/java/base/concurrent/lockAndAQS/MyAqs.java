package base.concurrent.lockAndAQS;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @author: 张灯皖
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class MyAqs extends AbstractQueuedLongSynchronizer {
    protected MyAqs() {
        super();
    }

    @Override
    protected boolean tryAcquire(long arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(long arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected long tryAcquireShared(long arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(long arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
    }
}
