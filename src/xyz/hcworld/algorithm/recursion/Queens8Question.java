package xyz.hcworld.algorithm.recursion;

/**
 * 八皇后问题-回溯法
 *
 * @ClassName: Queens8Question
 * @Author: 张红尘
 * @Date: 2020/4/28 19:39
 * @Version： 1.0
 */
public class Queens8Question {
    /**
     * 皇后个数
     */
    private int max = 8;
    /**
     * 存放皇后位置的结果 如array = {0,4,7,5,2,6,1,3}
     */
    private int[] array = new int[max];
    /**
     * 解法个数
     */
    static int sCount = 0;
    /**
     * 冲突次数
     */
    static int sJudgeCount=0;

    /**
     * 放置第n个皇后
     * check是每一次递归时，进入到check中都有for循环
     *
     * @param n 第n个皇后
     */
    private void check(int n) {
        //n=8 8个皇后放好了
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            //把当前皇后n，放到该行的第1列
            array[n] = i;
            //当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //不冲突，接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，继续执行array[n]=i;即将第n个皇后放置在本行的后移一个位置
        }
    }

    /**
     * 检查前面摆放的皇后是否与即将放置的n皇后是否冲突
     *
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n) {
        sJudgeCount++;
        for (int i = 0; i < n; i++) {
            /*
              array[i]==array[n]判断第n个皇后是否和前面的n-1个皇后同一列
              math.abs 求绝对值
              Math.abs(n-i)==Math.abs(array[n]-array[i])判断第n个皇后是否和前面的n-1个皇后是否在同一斜线
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出皇后摆放位置
     */
    private void print() {
        sCount++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //运行
        Queens8Question queens8Question = new Queens8Question();
        queens8Question.check(0);
        System.out.printf("一共有%d种解法\n", sCount);
        System.out.printf("一共判断冲突%d次\n", sJudgeCount);
    }
}
