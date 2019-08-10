package sourcecode.collection;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @name
 * @ClassName CollectionTest
 * @Description 集合操作类
 * @author: zdw
 * @date 2019/8/10 14:51
 */
public class CollectionTest {

    public static void main(String[] args) {
        Collection collection;
        Deque<String> deque = new LinkedList();
        deque.add("1");
        String first = deque.getFirst();
        System.out.println(first);
    }
}
