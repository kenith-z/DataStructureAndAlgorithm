package xyz.hcworld.algorithm.sorting;

/**
 * @author : 张红尘
 * @version 1.0
 * @classDesc: 功能描述：(排序算法之冒泡排序)
 * @date 创建时间：2018年1月23日 下午11:27:44
 */
public class BubbleSort {

    public static void main(String[] args) {
        //要排序的数组
        int[] arr = {2, 99, 88, 77, 26, 78, 2384, 28, 66, 33, 78, 12, 34, 56, 789, 555, 1, 3, 8, 779};
        //数组长度
        int n = arr.length;
        System.out.println(arr.length);
        //冒泡算法核心
        //n个数排列只要循环n-1次（n指数组的长度）
        for (int i = 1; i < n - 1; i++) {
            //从第一位开始比较到最后一个没有比较的数，因为运算多少次就有多少个不用比较所以是n-i个
            for (int j = 0; j < n - i; j++) {
                if (arr[j + 1] > arr[j]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
        //打印数组
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

}
