package base.collection;


import com.alibaba.fastjson.JSON;

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
//        System.out.println(JSONObject.toJSONString(buildTreeOfList));
//        ergodicNode(integerNode);
//        midWhile(integerNode);
//        mid(integerNode);
        List<Integer> fullPath = getFullPath(integerNode, 222, null);
        System.out.println(JSON.toJSONString(fullPath));
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

    /**
     * 遍历树，层遍历
     *
     * @param treeNodeRoot
     */
    private static void ergodicNode(Node<Integer> treeNodeRoot) {
        Deque<Node<Integer>> treeQueue = new LinkedList<Node<Integer>>();
        treeQueue.add(treeNodeRoot);

        while (!treeQueue.isEmpty()) {
            Node<Integer> node = treeQueue.poll();

            if (node.left != null) {
                treeQueue.add(node.left);
            }
            System.out.println(node.value + ",");
            if (node.right != null) {
                treeQueue.add(node.right);
            }
        }
    }

    /**
     * 中序遍历递归算法
     *
     * @param treeNodeRoot
     */
    private static void midWhile(Node<Integer> treeNodeRoot) {
        if (treeNodeRoot == null) {
            return;
        }
        midWhile(treeNodeRoot.left);
        System.out.println(treeNodeRoot.value + ",");
        midWhile(treeNodeRoot.right);
    }

    /**
     * 中序遍历非递归算法
     * <p>
     * 1)若其左孩子不为空，则将P入栈并将P的左孩子置为当前的P，然后对当前结点P再进行相同的处理；
     * 2)若其左孩子为空，则取栈顶元素并进行出栈操作，访问该栈顶结点，然后将当前的P置为栈顶结点的右孩子；
     * 3)直到P为NULL并且栈为空则遍历结束
     *
     * @param treeNodeRoot
     */
    private static void mid(Node<Integer> treeNodeRoot) {
        Stack<Node<Integer>> stack = new Stack<>();
        Node<Integer> node = treeNodeRoot;

        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);

                node = node.left;
            }
            if (!stack.empty()) {
                node = stack.pop();
                System.out.println(node.value + ",");
                node = node.right;
            }
        }
    }

    /**
     * 根据叶子节点的值得到完整的寻找路径 1.先找左边，再找右边,无论是哪边，递归技术条件都是找到叶子节点为止
     *
     * @param root     根节点
     * @param leftNode 叶子节点
     * @param list
     * @return
     */
    private static List<Integer> getFullPath(Node<Integer> root, final int leftNode, Stack<Integer> list) {
        //递归返回条件
        if (root == null) {
            return null;
        }
        if (list == null) {
            list = new Stack<Integer>();
        }

        list.push(root.value);

        System.out.println("value:" + root.value);
        //找到了
        if (root.value == leftNode) {
            return list;
        }
        System.out.println("toLeft");

        //往左边找
        List<Integer> leftList = getFullPath(root.left, leftNode, list);

        //找到了
        if (leftList != null) {
            return leftList;
        }

        System.out.println("toRight");
        //往右边找
        List<Integer> rightList = getFullPath(root.right, leftNode, list);

        //找到了
        if (rightList != null) {
            return rightList;
        }
        System.out.println("pop");

        list.pop();
        return null;
    }
}
