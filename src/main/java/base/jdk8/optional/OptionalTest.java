package base.jdk8.optional;

import base.jdk8.bean.User;

import java.util.Optional;
import java.util.function.BiFunction;

/**
 * @author: zdw.sheng@foxmail.com
 * @name:
 * @desc Supplier<T>: 数据提供器，可以提供 T 类型对象；无参的构造器，提供了 get 方法；
 * <p>
 * Function<T,R>: 数据转换器，接收一个 T 类型的对象，返回一个 R类型的对象； 单参数单返回值的行为接口；提供了 apply, compose, andThen, identity 方法;
 * <p>
 * Consumer<T>: 数据消费器， 接收一个 T类型的对象，无返回值，通常用于设置T对象的值； 单参数无返回值的行为接口；提供了 accept, andThen 方法;
 * <p>
 * Predicate<T>: 条件测试器，接收一个 T 类型的对象，返回布尔值，通常用于传递条件函数； 单参数布尔值的条件性接口。提供了 test (条件测试) , and-or- negate(与或非) 方法。
 * @jdk
 * @group
 * @os
 * @date 2018/11/13
 */
public class OptionalTest {

    public static void main(String[] args) {
//        getElse();
//        orElseGet();
//        orElseThrow();
//        optionalFilter();
//        optionalMap();
//        optionalFlatMap();
//        optionalIsParent();
        test();
    }

    private static void getElse() {
//        User user = null;
        User user = new User(23, "张笙", '男');
        //of()不能传入为null的参数，不然会报空指针异常
//        Optional<User> optionalUser = Optional.of(user);

        //user--age:23　name:张笙 sex:男
        Optional<User> optionalUser = Optional.ofNullable(user);
        user = optionalUser.orElse(new User());
        System.out.println(user);
    }

    /**
     * public T orElseGet(Supplier<? extends T> other)
     */
    private static void orElseGet() {
        User user = null;
//        User user = new User(23, "张笙", '男');
        //of()不能传入为null的参数，不然会报空指针异常
//        Optional<User> optionalUser = Optional.of(user);


        Optional<User> optionalUser = Optional.ofNullable(user);
        //orELseGet 如果不存在就执行方法，返回自己定义的一个新的泛型T    user--age:20　name:orElseGet sex:女
        user = optionalUser.orElseGet(() -> new User(20, "orElseGet", '女'));
        System.out.println(user);
    }

    private static void orElseThrow() {
        User user = null;
        Optional.ofNullable(user).orElseThrow(() -> new NullPointerException("参数user对象 不能为 null~~~~~~~~~~ "));
    }

    /**
     * public Optional<T> filter(Predicate<? super T> predicate)
     * <p>
     * 注意Optional中的filter方法和Stream中的filter方法是有点不一样的，Stream中的filter方法是对一堆元素进
     * 行过滤，而Optional中的filter方法只是对一个元素进行过滤，可以把Optional看成是最多只包含一个元素
     * 的Stream。
     */
    private static void optionalFilter() {
        User user = null;
        User myUser = new User(15, "张笙", '男');
        //user--age:15　name:张笙 sex:男
        user = Optional.ofNullable(myUser).filter(user1 -> user1.getAge() < 20).orElse(new User(2, "创造年龄小于二十的user", 'Y'));


        //user--age:2　name:创造年龄小于二十的user sex:Y
        user = Optional.ofNullable(user).filter(user1 -> user1.getAge() < 20).orElse(new User(2, "创造年龄小于二十的user", 'Y'));
        System.out.println(user.toString());
    }


    /**
     * public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
     * <p>
     * map中获取的返回值自动被Optional包装,即返回值 -> Optional<返回值>
     * <p>
     * flatMap中返回值保持不变,但必须是Optional类型,即Optional<返回值> -> Optional<返回值>
     */
    private static void optionalMap() {
        User user = null;
        User myUser = new User(23, "张笙", '男');
        Optional<User> optionalUser = Optional.ofNullable(Optional.ofNullable(myUser).map(e -> {
            e.setAge(88);
            e.setName("通过map把名字涨笙换成这句话");
            return e;
        }).orElse(new User()));
        Optional<String> name = Optional.ofNullable(user).map(u -> u.getName());
        System.out.println("姓名:" + name);

        System.out.println(optionalUser.get().toString());
//        Optional.ofNullable(user).flatMap()
    }

    /**
     * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
     */
    private static void optionalFlatMap() {
        User user = null;
        User us = null;
        User myUser = new User(23, "张笙", '男');
        user = Optional.ofNullable(user).flatMap(e -> Optional.ofNullable(e)).orElse(new User());
        String name = Optional.ofNullable(us).
                flatMap(u -> Optional.ofNullable(u.getName())).orElse("这是flatMap");
        System.out.println("name:" + name);
        System.out.println(user.toString());
    }

    /**
     * Optional.ofNullable(user).ifPresent();
     * public void ifPresent(Consumer<? super T> consumer)
     */
    private static void optionalIsParent() {
        User user = null;
        User myUser = new User(29, "如果存在ifPresent则输出", '男');
        Optional.ofNullable(myUser).ifPresent(e -> {
            System.out.println(e.toString());
        });
    }

    private static void test() {
        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::valueOf;
        Integer integer = Optional.ofNullable("1").map(Integer::valueOf).orElse(2);
        System.out.println("参数::引用--:" + integer);
        Integer integer1 = Optional.ofNullable("5").map(Integer::valueOf).filter(Double::isNaN).orElse(88);
        System.out.println("断言用法:" + integer1);
    }
}
