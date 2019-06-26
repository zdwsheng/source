package sourcecode.framework.spring.aop;


import sourcecode.framework.spring.aop.staticAgent.Man;
import sourcecode.framework.spring.aop.staticAgent.Person;

import java.lang.reflect.Proxy;

/**
 * @ClassName: JdkAop
 * @Author zdw
 * @Desc 基于jdk实现动态代理，只能基于接口
 *       因为动态代理是生成一个新的类，$proxy0(num).class;
 *       这个类需要继承Proxy类，然后实现代理类的接口，因为java是单继承，所以只能代理实现接口的类
 * @Date 2019/1/20 9:10
 */
public class JdkAop {
    /**
     * @param interfaceClazz 参数必须为接口类型，不然会报错
     * @param proxy
     * @return
     */
    public static Object jdkAgent(Class interfaceClazz, Object proxy) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
//                "true");
        JdkInvocationHandler handler = new JdkInvocationHandler(proxy);
        ClassLoader classLoader = interfaceClazz.getClassLoader();
        Class[] classArray = new Class[]{interfaceClazz};
        Object resultBean = Proxy.newProxyInstance(classLoader, classArray, handler);
        return resultBean;
    }

    public static void main(String[] args) {
        Person person =
                (Person) JdkAop.jdkAgent(Person.class, new Man("这是jdk的动态代理,Person类型的man"));
        //实际执行的是 JdkInvocationHandler.invoke(Object proxy, Method method, Object[] args)方法
        person.eat();
        person.say();
    }
}
