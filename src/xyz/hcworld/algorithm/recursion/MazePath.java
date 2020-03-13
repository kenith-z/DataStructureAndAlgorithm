package xyz.hcworld.algorithm.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 迷宫问题-回溯法
 *
 * @ClassName: MazePath
 * @Author: 张红尘
 * @Date: 2020/3/10 21:55
 * @Version： 1.0
 */
public class MazePath {
    public static void main(String[] args) {
        /*
            0为没走过的路径，1为墙，2表示通路，3表示该点已经走过但不通
            走迷宫策略为下->右->上->左，如果该点走不通，再回溯
        */
        //创建二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];

        //生成障碍坐标
        int[][] xyArray = {{3, 1}, {3, 2}};
        List<Map<String, Integer>> xyList = new ArrayList<>();
        Map<String, Integer> xy;
        for (int[] i : xyArray) {
            xy = new HashMap<>(5);
            xy.put("X", i[0]);
            xy.put("Y", i[1]);
            xyList.add(xy);
        }
        //创建地图
        createMaze(map);
        //生成障碍
        createObstacle(map, xyList);
        //遍历地图
        printMaze(map);
        System.out.printf("起点:(%d,%d)，终点:(%d,%d)", 2, 1, map[0].length - 1, map.length - 3);
        readyGo(map, 1, 1, map[0].length - 1, map.length - 3);
        //遍历地图
        printMaze(map);

    }

    /**
     * 创建迷宫
     *
     * @param maze 代表迷宫的数组
     */
    public static void createMaze(int[][] maze) {
        //使用1表示墙
        //上下全部置1
        for (int i = 0; i < maze[0].length; i++) {
            //第一行
            maze[0][i] = 1;
            //最后一行，获得行数-1
            maze[maze.length - 1][i] = 1;
        }
        //左右全部置1,因为第一（0）行和最后一行已经处理过，直接跳过
        for (int i = 1; i < maze.length - 1; i++) {
            //第一列
            maze[i][0] = 1;
            //最后一列所以是列数-1
            maze[i][maze[0].length - 1] = 1;
        }
    }

    /**
     * 生成障碍
     *
     * @param maze         迷宫地图
     * @param xyCoordinate 障碍坐标
     */
    public static void createObstacle(int[][] maze, List<Map<String, Integer>> xyCoordinate) {
        //判断是否有障碍物
        if (xyCoordinate.size() <= 0) {
            return;
        }
        //把指定位置设置成1即为成功添加障碍物
        for (Map<String, Integer> coordinate : xyCoordinate) {
            maze[coordinate.get("X")][coordinate.get("Y")] = 1;
        }
    }

    /**
     * 迷宫回溯算法
     * @param maze    地图
     * @param xOrigin 起点x坐标
     * @param yOrigin 起点y坐标
     * @param xEnd    结束x坐标
     * @param yEnd    结束y坐标
     * @return 是否找到通路
     */
    public static boolean readyGo(int[][] maze, int xOrigin, int yOrigin, int xEnd, int yEnd) {
        //递归出口，当终点被设置为2时代表找到通路
        if (maze[xEnd][yEnd] == 2) {
            return true;
        } else {
            if (maze[xOrigin][yOrigin] == 0) {
                //假设改坐标可以走通
                maze[xOrigin][yOrigin] = 2;
                //执行策略 下->右->上->左
                if (readyGo(maze, xOrigin + 1, yOrigin, xEnd, yEnd)) {
                    //向下走
                    return true;
                } else if (readyGo(maze, xOrigin, yOrigin + 1, xEnd, yEnd)) {
                    //向右走
                    return true;
                } else if (readyGo(maze, xOrigin - 1, yOrigin, xEnd, yEnd)) {
                    //向上走
                    return true;
                } else if (readyGo(maze, xOrigin, yOrigin - 1, xEnd, yEnd)) {
                    //向左走
                    return true;
                } else {
                    //这个坐标走不通
                    maze[xOrigin][yOrigin] = 3;
                    return false;
                }
            } else {
                //如果maze[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }


    /**
     * 显示迷宫
     *
     * @param maze 迷宫数组
     */
    public static void printMaze(int[][] maze) {
        System.out.println("地图参数");
        System.out.printf("迷宫行数:%d\n迷宫列数:%d\n", maze.length, maze[0].length);
        //打印二维数组
        for (int[] aMap : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(aMap[j] + "\t");
            }
            System.out.println();
        }
    }
}
