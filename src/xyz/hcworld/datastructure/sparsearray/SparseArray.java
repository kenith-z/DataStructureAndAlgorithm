package xyz.hcworld.datastructure.sparsearray;

/*
 *********************************************************************************************************
 * 数据结构之稀疏数组                                                                                    *
 *********************************************************************************************************
 *      二维数组twoArray                        稀疏数组sparseArray                                      *
 *      0  0  0  0  0                稀疏数组行标	二维数组行	二维数组列	值                           *
 *      0  0  0  0  0                0	            5	        5	        2                            *
 *      0  0  0  0  2      →        1	            2	        4	        2                            *
 *      0  0  1  0  0                2	            3	        2 	        1                            *
 *      0  0  0  0  0                                                                                    *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      当一个数组大部分元素为同一个值的时候，可以用稀疏数组来保存不同元素的位置和值以达到压缩的作用     *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如棋盘，地图等                                                                                   *
 *********************************************************************************************************
 * 稀疏数组结构                                                                                          *
 *      第0列分别记录总行数，总列数，总个数                                                              *
 *      第1~n列分别记录元素坐在行，元素坐在列，元素数值                                                  *
 *********************************************************************************************************
 * 二维数组转稀疏数组                                                                                    *
 *      1.	遍历原始二维数组得到有效数据的个数sum                                                        *
 *      2.	根据sum创建稀疏数组sparseArray int[sum+1][3]                                                 *
 *      3.	将二维数组的有效数据存入到稀疏数组中                                                         *
 * 稀疏数组转二维数组                                                                                    *
 *      1.	先读取稀疏数组的第一行，根据第一行的数据创建二维数组如上图twoArray=int[5][5]                 *
 *      2.	再读取稀疏数组后几行数据并赋值给二维数组                                                     *
 *********************************************************************************************************
 */

/**
 * @ClassName: SparseArray
 * @Author: 张红尘
 * @Date: 2020/1/4 23:25
 * @Version： 1.0
 */
public class SparseArray {
    public static void main(String[] args) {

        // 创建一个5*5的二维数组

        // 0：表示用户没有操作 1：表示模拟用户操作1 2：表示模拟用户操作2
        int[][] twoArray = new int[5][5];
        // 给二维数组赋值如最上方的分布
        twoArray[3][2] = 1;
        twoArray[2][4] = 2;
//        twoArray[2][1] = 1;
//        twoArray[0][4] = 2;
        // 输出二维数组
        System.out.println("1.二维数组");
        for (int[] row : twoArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 二维数组转化稀疏数组

        // 1.	遍历原始二维数组得到有效数据（非0）的个数sum
        int sum = 0;
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray[0].length; j++) {
                sum = twoArray[i][j] != 0 ? sum + 1 : sum;
            }
        }
        //2.	根据sum创建稀疏数组sparseArray int[sum+1][3]
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值 00是二维数组的总行数，01是二维数组的总列数，02是有效数据
        sparseArray[0][0] = twoArray.length;
        sparseArray[0][1] = twoArray[0].length;
        sparseArray[0][2] = sum;
        // 3.	遍历二维数组并将二维数组的有效数据存入到稀疏数组中
        // 计数器，记录是第几个有效数据
        int count = 0;
        for (int i = 0; i < twoArray.length; i++) {
            for (int j = 0; j < twoArray[0].length; j++) {
                if (twoArray[i][j] == 0) {
                    continue;
                }
                count++;
                sparseArray[count][0] = i;
                sparseArray[count][1] = j;
                sparseArray[count][2] = twoArray[i][j];
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("2.稀疏数组");
        for (int[] row : sparseArray) {
            System.out.printf("%d\t%d\t%d\t\n", row[0], row[1], row[2]);
        }

        //稀疏数组转二维数组

        // 1.	先读取稀疏数组的第一行，根据第一行的数据创建二维数组如上图twoArray=int[5][5]
        //读取稀疏数组的第0行第0个元素获得二维数组的行，第0行第1个元素获得二维数组的列
        int[][] twoArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];
        System.out.println();
        System.out.println("3.创建后未还原数据的二维数组");
        for (int[] row : twoArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 2.	再读取稀疏数组后几行数据并赋值给二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            //sparseArray[i][0]是第几行，sparseArray[i][1]是第几列，sparseArray[i][2]是值
            twoArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //输出还原后的二维数组
        System.out.println();
        System.out.println("4.还原后的二维数组");
        for (int[] row : twoArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
