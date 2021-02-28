package xyz.hcworld.algorithm.sorting;

import java.util.Arrays;

/**
 * 排序算法之选择排序算法
 * @ClassName: SelectSort
 * @Author: 张红尘
 * @Date: 2021-02-28
 * @Version： 1.0
 */
public class SelectSort {

    public static void main(String[] args){
        int[] a = {6,3,8,2,9,1};
        int index;
        // 总共要经过 N-1 轮比较
        for (int i = 0;i<a.length-1;i++){
            index=i;
            // 每轮需要比较的次数 N-i
            for (int j = index;j<a.length-1;j++){
                if(a[j+1]<a[index]){
                    // 记录目前能找到的最小值元素的下标
                    index=j+1;
                }
            }
            int temp = a[index];
            a[index] = a[i];
            a[i]=temp;
        }
        System.out.println(Arrays.toString(a));
        for (int i =0;i< a.length-1;i++){
            index = i;
            for (int j =index;j< a.length-1;j++){
                if(a[j+1]>a[index]){
                    index = j+1;
                }
            }
            int temp = a[index];
            a[index] = a[i];
            a[i]=temp;
        }
        System.out.println(Arrays.toString(a));
    }

}
