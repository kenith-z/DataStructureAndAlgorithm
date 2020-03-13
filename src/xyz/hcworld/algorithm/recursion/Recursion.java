package xyz.hcworld.algorithm.recursion;

/**
 * 递归算法
 *
 * @ClassName: Recursion
 * @Author: 张红尘
 * @Date: 2020/3/5 21:49
 * @Version： 1.0
 */
public class Recursion {
    public static void main(String[] args) {
        recursiveDirList(5, 0);
        System.out.println();
        System.out.println(factorial(3));
    }

    /**
     * 打印问题
     *
     * @param n 操作数
     * @param m 出口数
     */
    public static void recursiveDirList(int n, int m) {
        //结果n1=5	n1=4	n1=3	n1=2	n1=1	n1=0
        System.out.printf("n1=%d\t", n);
        //递归出口的条件
        if (n > m) {
            //本体调用
            recursiveDirList(n - 1, m);
        }
        //结果n2=0	n2=1	n2=2	n2=3	n2=4	n2=5
        System.out.printf("\tn2=%d", n);
    }

    /**
     * 阶乘问题
     * @param n 阶乘到多少
     * @return 阶乘答案
     */
    public static int factorial(int n) {
        //递归结束条件
        if (n == 1) {
            return 1;
        } else {
            //不满足结束条件
            return factorial(n - 1) * n;
        }
    }
}
