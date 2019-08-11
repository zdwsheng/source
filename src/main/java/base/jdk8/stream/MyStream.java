package base.jdk8.stream;

import base.jdk8.bean.User;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public static void main(String[] args) {
       /* Map map = instance.listToMap();
        System.out.println(JSONObject.toJSONString(map));*/
//        instance.intStream();
        instance.parallelStream();
    }

    private Map listToMap() {
        List<User> userList = new LinkedList<>();
        userList.add(new User(5, "1", 'Y'));
        userList.add(new User(2, "2", 'Y'));
        userList.add(new User(4, "3", 'Y'));
        userList.add(new User(4, "3", 'Y'));
        /*//key不能重复
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getAge(), Function.identity()));*/
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getAge(), Function.identity(), (preUser, user) -> preUser));
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
}
