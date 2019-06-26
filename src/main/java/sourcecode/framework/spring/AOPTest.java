package sourcecode.framework.spring;

import sourcecode.framework.spring.aop.CglibAop;
import sourcecode.framework.spring.aop.JdkAop;
import sourcecode.framework.spring.aop.staticAgent.Man;
import sourcecode.framework.spring.aop.staticAgent.ManAgent;
import sourcecode.framework.spring.aop.staticAgent.Person;

/**
 * @ClassName: AOP
 * @Author zdw
 * @Desc 静态代理以及 spring aop : jdk动态代理和cglib动态代理
 * @Date 2019/1/20 9:06
 */
public class AOPTest {
    private static AOPTest aopTest = new AOPTest();

    public static void main(String[] args) {
//        aopTest.staticAgent();
//        aopTest.jdkAgent();
          aopTest.cglibAop();
    }

    private void staticAgent() {
        Person man = new Man("这是man,实际处理对象");
        ManAgent manAgent =
                new ManAgent(man, "manAgent执行业务开始之前.......", "......manAgent执行业务开始之后");
        manAgent.say();
        manAgent.eat();
    }

    private void jdkAgent() {
        Person person =
                (Person) JdkAop.jdkAgent(Person.class, new Man("这是jdk的动态代理,Person类型的man"));
        //实际执行的是 invoke(Object proxy, Method method, Object[] args)方法
        person.eat();
        person.say();
    }

    private void cglibAop() {
        CglibAop cglibAop = new CglibAop();
        Person person = (Person) cglibAop.getProxyInstance(new Man("....这是cglib aop..."));
        person.say();
    }
}
