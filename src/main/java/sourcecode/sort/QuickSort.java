package sourcecode.sort;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName QuickSort
 * @Description 快速排序 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
 * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * <p>
 * 算法描述
 * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
 * <p>
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，
 * 所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)
 * <p>
 * <p>
 * 一组数字，以第一个为基准，赋值给key，首先从最右边开始找到第一个比key小的数，然后赋值给第一个，再从左边找第一个比key大的数，赋值给右边一开始比key小的数
 * @author: zdw
 * @date 2019/9/11 9:41
 */
public class QuickSort {
    private static int[] numArray = new int[]{71, 415, 123, 5, 26, 31, 56, 62, 45};

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(numArray));
        System.out.println(JSON.toJSONString(quickSort(numArray, 0, numArray.length - 1)));
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //已经排好序的基准值index
            int partIndex = partArr(arr, left, right);
            quickSort(arr, left, partIndex - 1);
            quickSort(arr, partIndex + 1, right);
        }
        return arr;
    }

    /**
     * @return {@link null}
     * @throws
     * @description 根据基准值来降数组排序，比基准值小的全部放到基准值左边，比基准值大的都放到基准值右边
     * @author zdw
     * @date 2019/9/11
     */
    private static int partArr(int[] arr, int left, int right) {
        //基准值
        int benchmarkIndex = left;
        //左指针
        int index = benchmarkIndex + 1;
        for (int i = index; i <= right; i++) {
            //找到第小于基准值的指针位置，和左指针交换位置
            if (arr[i] < arr[benchmarkIndex]) {
                CommonMethod.swap(arr, i, index);
                //将左指针后移一位
                index++;
            }
        }
        //将最后一个比基准值小的index和基准值交换位置，这样基准值就完成了全部左小右大的功能
        CommonMethod.swap(arr, benchmarkIndex, index - 1);
        return index - 1;
    }
}
