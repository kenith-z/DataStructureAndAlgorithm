package xyz.hcworld.datastructure.queue;

import java.util.Scanner;

/**
 * @ClassName: QueueDemo
 * @Author: 张红尘
 * @Date: 2020/1/6 0:54
 * @Version： 1.0
 */
public class QueueDemo {

    public static void main(String[] args) {
        //创建一个队列
        //ArrayQueue queue = new ArrayQueue(3);
        //创建一个环形队列,实际容量为最大容量-1
        CircuitArrayQueue queue = new CircuitArrayQueue(4);
        System.out.println("环形队列");
        //接受用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println();
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            System.out.println();
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    //查看队列数据
                    queue.showQueue();
                    break;
                case 'a':
                    //增加一个数据
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    //查看队列头数据
                    try {
                        int res = queue.hradQueue();
                        System.out.printf("队列头的数据是%d", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
