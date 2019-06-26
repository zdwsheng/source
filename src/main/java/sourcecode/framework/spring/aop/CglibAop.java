package sourcecode.framework.spring.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibAop
 * @Author zdw
 * @Desc cglib基于继承实现代理，所以无法对final类和static,private方法进行代理
 *       可以代理protected方法，但是必须在同一个包下面(无意义，一般引用jar，不会再一个包下面)
 * @Date 2019/1/20 9:10
 */
public class CglibAop implements MethodInterceptor {
    private Object proxy;

    public CglibAop(){}
    public Object getProxyInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        //superClass是谁
        enhancer.setSuperclass(this.proxy.getClass());
        // 回调方法,就是要织入的代码
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        this.before();
        Object resultBean = methodProxy.invokeSuper(o, args);
        this.after();
        return resultBean;
    }

    private void before() {
        System.out.println("before cglibAop........");
    }

    private void after() {
        System.out.println(".........after cglibAop");
    }
}
