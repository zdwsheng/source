package sourcecode.framework.spring.aop.staticAgent;

/**
 * @ClassName: ManAgent
 * @Author zdw
 * @Desc 静态代理业务类
 * @Date 2019/1/20 9:36
 */
public class ManAgent implements Person {
    //实际执行业务对象
    private Person person;
    private String beforeSay = null;
    private String afterSay = null;

    public ManAgent(Person person, String beforeSay, String afterSay) {
        this.person = person;
        this.beforeSay = beforeSay;
        this.afterSay = afterSay;
    }

    @Override
    public void say() {
        this.beforeSay();
        person.say();
        this.afterSay();
    }

    @Override
    public void eat() {
        this.beforeSay();
        person.eat();
        this.afterSay();
    }

    private void beforeSay() {
        System.out.println("代理之前:" + beforeSay);
    }

    private void afterSay() {
        System.out.println("代理之后:" + afterSay);
    }
}
