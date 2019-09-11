package sourcecode.sort;

/**
 * @ClassName CommonMethod
 * @Description 算法公共方法
 * @author: zdw
 * @date 2019/9/11 10:40
 */
public class CommonMethod {
    private CommonMethod() {
    }

    /**
     * @return {@link null}
     * @throws
     * @description 交换数组两个位置的值
     * @author zdw
     * @date 2019/9/11
     */
    public static final void swap(int[] array, int leftIndex, int rightIndex) {
        int temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }
}
