package sourcecode.fx;

import java.util.*;

/**
 * @name
 * @ClassName MYFx
 * @Description 泛型
 * @author: zdw
 * @date 2019/8/10 21:36
 */
public class MYFx<T, R> extends HashMap {
    private int temp = 0;

    private MYFx() {
    }

    private static final MYFx instance = new MYFx();

    public static void main(String[] args) {
        MYFx myFx = new MYFx();
        System.out.println(instance.hello(myFx, "T R泛型"));

        Child child = new Child();
        Set<Child> myFxSet = new HashSet<>();
        myFxSet.add(child);
        System.out.println(instance.fxExtends(myFxSet));

        System.out.println(instance.fxSuper(myFxSet));

        System.out.println(instance.fxW(myFxSet));

    }

    /**
     * 接受 <T> 类型参数</>
     *
     * @param objT
     * @param objR
     * @param <T>
     * @return
     */
    private <T, R> String hello(T objT, R objR) {
        Set<T> singleton = Collections.singleton(objT);
        return singleton.toString() + objR.toString();
    }

    private String fxExtends(Collection<? extends MYFx> collection) {
        return "Collection<? extends MYFx>" + collection.size();
    }

    private String fxSuper(Collection<? super MYFx> collection) {
        return "Collection<? super MYFx>" + collection.size();
    }

    private String fxW(Collection<?> collection) {
        return "问号泛型" + collection.isEmpty();
    }

    static class Child extends MYFx {

    }
}
