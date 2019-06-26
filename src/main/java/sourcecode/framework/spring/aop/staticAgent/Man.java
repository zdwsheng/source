package sourcecode.framework.spring.aop.staticAgent;

/**
 * @ClassName: Man
 * @Author zdw
 * @Desc 静态代理具体实现类
 * @Date 2019/1/20 9:35
 */
public class Man implements Person {
    String sayWords = null;

    public Man() {
    }

    public Man(String sayWords) {
        this.sayWords = sayWords;
    }

    @Override
    public void say() {
        System.out.println("Man.class  imp Method person say():" + this.sayWords);
    }

    @Override
    public void eat() {
        System.out.println("Man.class  imp Method person eat():" + this.sayWords);
    }

    protected void sing() {
        System.out.println("protected sing.....");
    }
}
