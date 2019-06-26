package sourcecode.framework.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: JdkInvocationHandler
 * @Author zdw
 * @Desc TODO
 * @Date 2019/1/20 9:17
 */
public class JdkInvocationHandler implements InvocationHandler {

    Object proxy;

    JdkInvocationHandler(Object proxy) {
        this.proxy = proxy;
    }

    /**
     *
     * @param proxy : 被代理的类的实例
     * @param method :调用被代理的类的方法
     * @param args: 该方法需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.sayBefore();
        Object result = method.invoke(this.proxy, args);
        this.sayAfter();
        return result;
    }

    private void sayBefore() {
        System.out.println("before jdkAop...........");
    }

    private void sayAfter() {
        System.out.println("..............after jdkAop");
    }

}
