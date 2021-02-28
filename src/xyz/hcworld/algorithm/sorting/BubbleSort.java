package xyz.hcworld.algorithm.sorting;

import java.util.Arrays;

/**
 * @author : 张红尘
 * @version 1.0
 * @classDesc: 功能描述：(排序算法之冒泡排序)
 * @date 创建时间：2018年1月23日 下午11:27:44
 */
public class BubbleSort {

    public static void main(String[] args) {
        //要排序的数组
        int[] arr = {3,9,8,6,7,2,3,4,1,5};
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
        //[2384, 789, 779, 555, 99, 88, 78, 78, 77, 66, 56, 34, 33, 28, 26, 12, 8, 3, 2, 1]
        System.out.println(Arrays.toString(arr));
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        //[1, 2, 3, 8, 12, 26, 28, 33, 34, 56, 66, 77, 78, 78, 88, 99, 555, 779, 789, 2384]
        System.out.println(Arrays.toString(arr));

    }

}
