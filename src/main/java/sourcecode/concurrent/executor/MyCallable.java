package sourcecode.concurrent.executor;

import java.util.concurrent.Callable;

/**
 * @author: 张灯皖
 * @name
 * @desc TODO
 * @jdk
 * @group
 * @os
 * @date 2019/1/12
 */
public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        String returnStr = "this is my imp callable";
        System.out.println(returnStr);
        return new String(returnStr + " return");
    }
}
