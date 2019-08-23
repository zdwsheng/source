package base.jdk8.stream;

import base.jdk8.bean.User;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.function.DoublePredicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static java.lang.System.out;

/**
 * @name
 * @ClassName MyStream
 * @Description stream操作
 * @author: zdw
 * @date 2019/7/13 18:22
 */
public class MyStream {
    private static final MyStream instance = new MyStream();

    private List<User> userList = new LinkedList<>();

    {
        userList.add(new User(5, "1", 'Y'));
        userList.add(new User(20, "2", 'Y'));
        userList.add(new User(4, "3", 'Y'));
        userList.add(new User(4, "3", 'Y'));
    }

    public static void main(String[] args) {
        Map map = instance.listToMap();
        System.out.println(JSONObject.toJSONString(map));
//        instance.intStream();
//        instance.parallelStream();
//        instance.reduceStream();
        instance.collect();
    }

    private Map listToMap() {
        /*//key不能重复
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getAge(), Function.identity()));*/
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getAge, Function.identity(), (preUser, user) -> preUser));
//        userMap = userList.stream().collect(HashMap::new, (m, v) -> m.put(v.getAge(), v), Map::putAll);
        return userMap;
    }

    private void intStream() {
        double asDouble = IntStream.range(1, 2).average().getAsDouble();
        out.println("IntStream.range(1, 2).average().getAsDouble():" + asDouble);
        double aDouble = IntStream.rangeClosed(3, 5).average().getAsDouble();
        out.println("IntStream.rangeClosed(3, 5).average().getAsDouble():" + aDouble);
        int asInt = IntStream.of(1, 2, 3, 4, 5).max().getAsInt();
        out.println("IntStream.of(1, 2, 3, 4, 5).max().getAsInt():" + asInt);
        DoublePredicate range = d -> d > 1 && d < 4;
        DoubleStream.of(1, 2, 3, 4, 5).filter(range).forEach(d -> out.println(d));
    }

    /**
     * 并行流处理  ForkJoinPool
     */
    private void parallelStream() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEachOrdered(out::println);
    }

    private void reduceStream() {

        Integer totalAge = userList.stream().map(User::getAge).filter(Objects::nonNull).reduce(0, (a, b) -> a + b);
        out.println("totalAge:" + totalAge);
        //未指定初始值，所以是返回的Optional
        Optional<Integer> reduceAgeOptional = userList.stream().map(User::getAge).filter(Objects::nonNull).reduce((a, b) -> a + b);
        out.println("totalAge:" + reduceAgeOptional.orElseGet(() -> 0));
        Integer max = userList.stream().map(User::getAge).filter(Objects::nonNull).reduce(Integer::max).get();
        out.println("最大值:" + max);
    }

    /**
     * collect(Supplier<R> supplier,初始容器
     * BiConsumer<R, ? super P_OUT> accumulator, 加入到容器操作
     * BiConsumer<R, R> combiner) 多容器聚合操作
     * collect(StringBuilder::new, StringBuilder::append,StringBuilder::append)
     * <p>
     * <p>
     * <p>
     * CollectorImpl(Supplier<A> supplier, 初始容器
     * BiConsumer<A, T> accumulator, 加入到容器操作
     * BinaryOperator<A> combiner, 多容器聚合操作
     * Function<A,R> finisher,聚合后的结果操作
     * Set<Characteristics> characteristics) 操作中便于优化的状态字段
     */
    private void collect() {
        String collectAge = userList.stream().map(User::getAge).map(String::valueOf).collect(Collectors.joining(","));
        out.println(collectAge);
        String name = userList.stream().map(User::getName).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        out.println("name:" + name);
        HashSet<User> userSet = userList.stream().collect(HashSet::new, Set::add, Set::addAll);
        out.println(userSet.isEmpty());
    }

}
