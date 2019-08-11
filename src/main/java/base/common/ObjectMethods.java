package base.common;

/**
 * @author: 张灯皖
 * @name
 * @desc
 * @jdk
 * @group
 * @os
 * @date 2018/11/19
 */
public class ObjectMethods {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        obj.equals("1");

        obj.hashCode();

        obj.toString();

        obj.getClass();

        obj.wait();
        obj.wait(1000);
        obj.wait(1000, 1);

        obj.notify();
        obj.notifyAll();

        /**
         * protected native Object clone() throws CloneNotSupportedException;
         */

        /**
         *  private static native void registerNatives();
         *     static {
         *         registerNatives();
         *     }
         */

        /**
         *  protected void finalize() throws Throwable { }
         */
    }
}
