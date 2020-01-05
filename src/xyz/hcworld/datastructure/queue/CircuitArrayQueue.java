package xyz.hcworld.datastructure.queue;

/*
 *********************************************************************************************************
 * 数据结构之环形队列                                                                                    *
 *********************************************************************************************************
 * MaxSize-1[     ]<-rear =n-1     MaxSize-1[     ]<-rear =n-1     MaxSize-1[-----]                      *
 *         .[-----]                        .[-----]                        .[-----]                      *
 *         3[-----]             →         3[-----]             →         3[-----]                      *
 *         2[-----]             取         2[-----]<-front=2    存         2[-----]<-front=2             *
 *         1[-----]                        1[     ]                        1[     ]<-rear =1             *
 *         0[-----]<-front=-1              0[     ]                        0[-----]                      *
 *           Queue                           Queue                           Queue                       *
 *  队列弹出元素后front指向元素2继续补充队列到满时则rear指向1，rear+1=3  则3%5=3，而front指向3则队列[满] *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      与队列的基本概念一样。                                                                           *
 *      1.	front:指向队列第一个元素，初始默认值为0                                                      *
 *      2.	rear:指向队列的最后一个元素的后一个位置,初始默认值为0                                        *
 *      3.	当队列满时，条件是(rear+1)%maxSize=front                                                     *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如：叫号系统，秒杀系统，高并发情况下API限流等     可以循环使用                                   *
 *********************************************************************************************************
 * 队列结构                                                                                              *
 *      maxSize:队列最大容量     实际容量为maxSize-1                                                     *
 *      front:队列头的下标 指向队列第一个元素，初始默认值为0                                             *
 *      rear:队列尾的下标 指向队列的最后一个元素的后一个位置,初始默认值为0                               *
 *      queue:队列本身                                                                                   *
 *********************************************************************************************************
 * 队列添加数据                                                                                          *
 *      1.	先判断队列是否已满                                                                           *
 *      2.	(rear + 1) % maxSize == front对比，相等则队列已满                                            *
 *              注:队列从0开始所以队列最后一个元素的下标为maxSize-1                                      *
 *      3.	未满则数据存入queue[rear]                                                                    *
 *      4.  将rear后移一位(求模防止数组越界)        rear = (rear + 1) % maxSize                          *
 * 队列取出数据                                                                                          *
 *      1.	先判断队列是否为空                                                                           *
 *      2.	rear与front对比，相等则代表队列为空                                                          *
 *      3.	不空则先把queue[front]保存的到临时变量                                                       *
 *      4.  将front后移一位,取模防止数组越界   (front + 1) % maxSize                                     *
 *      5.  将临时保存的变量返回                                                                         *
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
public class CircuitArrayQueue {
    /**
     * 队列最大容量<br>
     * 实际容量为maxSize-1
     */
    private int maxSize;
    /**
     * 队列头的下标 指向队列第一个元素，初始默认值为0
     */
    private int front;
    /**
     * 队列尾的下标 指向队列的最后一个元素的后一个位置,初始默认值为0
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
    public CircuitArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        array = new int[maxSize];
    }

    /**
     * 判断队列是否满<br>
     * 例子：maxSize=5时队列满即rear指向4即队列的最后一个元素，rear+1=5  则5%5=0，而front指向0则队列[满]
     *
     * @return true: 队列满;
     * false:队列未满
     */
    public boolean sizeFull() {
        return (rear + 1) % maxSize == front;
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
        //存进数据到队列中
        array[rear] = n;
        //rear后移，这里必须取模防止数组越界
        rear = (rear + 1) % maxSize;
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
        /*
            1.先把front指向的值保存的到临时变量
            2.将front后移一位,取模防止数组越界
            3.将临时保存的变量返回
        */
        int value = array[front];
        front = (front + 1) % maxSize;
        return value;
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
        //从front开始遍历
        for (int i = front; i < front + queueSize(); i++) {
            System.out.printf("arr[%d]=%d", i % maxSize, array[i % maxSize]);
        }
    }

    /**
     * 队列的有效数据
     *
     * @return 队列的有效数据个数
     */
    public int queueSize() {
        /* rear=2
         * front=1
         * maxSize=5
         * */
        return (rear + maxSize - front) % maxSize;
    }


    /**
     * 显示队列头数据，不是取数据
     *
     * @return 队列头数据，front不后移
     */
    public int hradQueue() {
        if (sizeEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        return array[front];
    }


}
