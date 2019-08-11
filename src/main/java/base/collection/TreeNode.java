package base.collection;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @name
 * @ClassName TreeNode
 * @Description 自定义二叉树
 * @author: zdw 借鉴晓风轻
 * @date 2019/8/11 10:21
 */
public class TreeNode<E> {

    public static void main(String[] args) {
        Node<Integer> integerNode = buildTree(1, 3, 5, 7, 9, 22, 34, 12);
        Node<Integer> buildTreeOfList = buildTreeOfList(1, 3, 5, 7, 9, 22, 34, 12);
        System.out.println(JSONObject.toJSONString(buildTreeOfList));
    }


    /**
     * 树节点
     */
    static class Node<E> {
        private E value;

        private Node<E> left, right;

        public Node(E value) {
            this.value = value;
        }
    }


    /**
     * 使用队列，按层便利tree desc:将第一个元素作为根节点，然后判断左右是否满了，如果满了，就弹出第一个，否则将元素放入队列中
     *
     * @param elements
     * @param <E>
     * @return
     */
    public static <E> Node<E> buildTree(E... elements) {
        Deque<Node<E>> treeNodeDeque = new ArrayDeque<>();

        Node<E> root = new Node<>(elements[0]);
        treeNodeDeque.add(root);

        for (int i = 1; i < elements.length; i++) {
            E value = elements[i];

            Node<E> node = new Node<>(value);

            Node<E> headNode = treeNodeDeque.peekFirst();

            if (Objects.isNull(headNode.left)) {
                headNode.left = node;
            } else if (Objects.isNull(headNode.right)) {
                headNode.right = node;
                //删除第一个元素
                treeNodeDeque.pop();
            }
            treeNodeDeque.add(node);
        }
        return root;
    }


    /**
     * 使用list遍历，和上面一样的思想
     *
     * @param elements
     * @param <E>
     * @return
     */
    public static <E> Node<E> buildTreeOfList(E... elements) {
        List<Node> nodeList = new ArrayList<>(elements.length);
        Node<E> root = null;

        for (int i = 0; i < elements.length; i++) {
            E value = elements[i];
            Node<E> node = new Node<>(value);
            if (Objects.isNull(root)) {
                root = node;
            }
            if (!nodeList.isEmpty()) {
                Node currentNode = nodeList.get(0);
                if (currentNode.left == null) {
                    currentNode.left = node;
                } else if (currentNode.right == null) {
                    currentNode.right = node;
                    nodeList.remove(0);
                }
            }
            nodeList.add(node);
        }
        return root;
    }
}
