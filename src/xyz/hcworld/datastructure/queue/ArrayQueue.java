package xyz.hcworld.datastructure.queue;

/*
 *********************************************************************************************************
 * 数据结构之队列                                                                                        *
 *********************************************************************************************************
 * MaxSize-1[     ]                MaxSize-1[     ]                MaxSize-1[     ]                      *
 *         .[     ]                        .[     ]                        .[     ]                      *
 *         3[     ]             →         3[-----]<-rear = 3   →         3[-----]<-rear = 3            *
 *         2[     ]             存         2[-----]             取         2[-----]<-front= 2            *
 *         1[     ]                        1[-----]                        1[     ]                      *
 *         0[     ]                        0[-----]                        0[     ]                      *
 *           Queue <-rear =-1                Queue <-front=-1                Queue                       *
 *                 <-front=-1                                                                            *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      按队列是一个有序列表，可以用数组和链表实现。数组顺序存储，链表链式存储。                         *
 *      遵循先入先出的原则，即先存入队列的数据，要先取出，后存入的要后取出。                             *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如：叫号系统，秒杀系统，高并发情况下API限流等 但是这种队列只能使用一次                           *
 *********************************************************************************************************
 * 队列结构                                                                                              *
 *      maxSize:队列最大容量                                                                             *
 *      front:队列头的下标（相当于C语言链表的的头指针）随着数据输出而改变                                *
 *      rear:队列尾的下标（相当于C语言链表的的尾指针）随着数据输入而改变                                 *
 *      queue:队列本身                                                                                   *
 *********************************************************************************************************
 * 队列添加数据                                                                                          *
 *      1.	先判断队列是否已满                                                                           *
 *      2.	rear与maxSize-1对比，相等则队列已满 注:队列从0开始所以队列最后一个元素的下标为maxSize-1      *
 *      3.	未满则rear后移一位，并将数据存入queue[rear]                                                  *
 * 队列取出数据                                                                                          *
 *      1.	先判断队列是否为空                                                                           *
 *      2.	rear与front对比，相等则代表队列为空                                                          *
 *      3.	不空则front后移一位,并将数据从queue[front]取出                                               *
 *********************************************************************************************************
 */

/**
 * 数组模拟队列
 *
 * @ClassName: ArrayQueue
 * @Author: 张红尘
 * @Date: 2020/1/5 15:47
 * @Version： 1.0
 */
public class ArrayQueue {
    /**
     * 队列最大容量
     */
    private int maxSize;
    /**
     * 队列头
     */
    private int front;
    /**
     * 队列尾
     */
    private int rear;
    /**
     * 存放数据，模拟队列
     */
    private int[] array;

    /**
     * 队列的构造方法
     *
     * @param arrMaxSize 队列容量最大值
     */
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        array = new int[maxSize];
        //指向队列头部，是指向队列第一个数据的的前一个位置
        front = -1;
        //指向队列，指向队列尾的具体数据（队列最后一个数据）
        rear = -1;
    }

    /**
     * 判断队列是否满
     *
     * @return true: 队列满;
     * false:队列未满
     */
    public boolean sizeFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return true: 队列为空;
     * false:队列不空
     */
    public boolean sizeEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n 要添加的数据
     */
    public void addQueue(int n) {
        //判断队列是否满
        if (sizeFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        //让rear后移一位再存进数据到队列中
        array[++rear] = n;
    }

    /**
     * 取出队列的数据，front后移
     *
     * @return 出队列的数据
     */
    public int getQueue() {
        if (sizeEmpty()) {
            //抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //front后移一位再队列中取出数据
        return array[++front];
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        //遍历
        if (sizeEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("arr[%d]=%d", i, array[i]);
        }
    }

    /**
     * 显示队列头数据，不是取数据
     * @return 队列头数据，front不后移
     */
    public int headQueue() {
        if (sizeEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        return array[front + 1];
    }


}
