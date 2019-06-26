package sourcecode.leetcode;

/**
 * @name
 * @ClassName Code_001
 * @Description https://leetcode-cn.com/problems/two-sum/
 * @author: zdw
 * @date 2019/4/9 13:47
 */

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
 * 并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Code_001 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, -2, -3, -4, -5};
        int target = -8;
        int[] position = findByMap(nums, target);
        System.out.println(JSON.toJSONString(position));
    }

    /**
     * 循环遍历
     *
     * @param numsArray
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numsArray, int target) {
        int[] position = new int[2];
        for (int i = 0; i < numsArray.length; i++) {
            int num = numsArray[i];
            for (int j = i + 1; j < numsArray.length; j++) {
                int next = numsArray[j];
                if (num + next == target) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        return position;
    }

    /**
     * 先算出差值，再找出差值
     *
     * @param numsArray
     * @param target
     * @return
     */
    public static int[] twoSumFind(int[] numsArray, int target) {
        int[] position = new int[2];
        for (int i = 0; i < numsArray.length; i++) {
            int num = numsArray[i];
            int disparity = target - num;
            for (int j = i + 1; j < numsArray.length; j++) {
                if (numsArray[j] == disparity) {
                    position[1] = j;
                    position[0] = i;
                    return position;
                }
            }
        }
        return position;
    }

    /**
     * 利用map计算
     *
     * @param numsArray
     * @param target
     * @return
     */
    public static int[] findByMap(int[] numsArray, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int[] position = new int[2];
        for (int i = 0; i < numsArray.length; i++) {
            if (numMap.containsKey(target - numsArray[i])) {
                position[0] = numMap.get(target - numsArray[i]);
                position[1] = i;
                return position;
            }
            numMap.put(numsArray[i], i);
        }
        return position;
    }
}
