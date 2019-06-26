package sourcecode.leetcode;

/**
 * @name
 * @ClassName Code_002
 * @Description https://leetcode-cn.com/problems/add-two-numbers/
 * @author: zdw
 * @date 2019/4/9 15:20
 */

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Code_002 {
    ListNode listNode = new ListNode(0);
    int[] numVal = new int[]{};

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void addTwoNumbers(ListNode firstNode, ListNode secondNode) {
       
    }

    public static void main(String[] args) {

    }

}
