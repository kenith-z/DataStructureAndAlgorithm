package xyz.hcworld.algorithm.sorting;

/**
 * @author : 张红尘
 * @version 1.0
 * @classDesc: 功能描述：(排序算法之直接插入排序)
 * @date 创建时间：2018年10月30日 下午2:14:02
 */
public class DirectInsertionSort {
    public static void main(String[] args) {
        int[] a = new int[]{9, 6, 11, 2, 10, 7, 3, 8, 1};
        int tem, j;
        for (int i = 1; i < a.length; i++) {
            //第i位比前一位小的时候
            if (a[i] < a[i - 1]) {
                //当前一位比I位大的时候，把第i位存到tem，再把前一位的值复制到i位
                tem = a[i];
                a[i] = a[i - 1];
                //把数据往后移
                for (j = i - 1; j >= 0 && a[j] > tem; j--) {
                    a[j + 1] = a[j];
                }
                //把tema[j]插
                a[j + 1] = tem;
            }
            for (int as : a) {
                //输出每一次对比的结果
                System.out.print(as + "\t");
            }
            System.out.print("\n");
        }
    }
}
