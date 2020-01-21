package xyz.hcworld.datastructure.linkedlist;

import xyz.hcworld.datastructure.linkedlist.node.NodeOfSingle;

import java.util.Stack;

/*
 *********************************************************************************************************
 * 数据结构之单向链表                                                                                    *
 *********************************************************************************************************
 *                                       内存结构          *             逻辑结构                        *
 *            头指针     地址  数据域(data)  指针域(next)  *  地址  数据域(data)  指针域(next)           *
 *      head [ 1200H ]  1000H  [    a2    ]  [  1800H   ]  *  1200H  [    a1    ]  [  1000H   ]          *
 *            尾指针    1100H  [          ]  [          ]  *  1000H  [    a2    ]  [  1800H   ]          *
 *      tail [ 1500H ]  1200H  [    a1    ]  [  1000H   ]  *  1800H  [    a3    ]  [  1300H   ]          *
 *                      1300H  [    a4    ]  [  1700H   ]  *  1300H  [    a4    ]  [  1700H   ]          *
 *                      1400H  [          ]  [          ]  *  1700H  [    a5    ]  [  1500H   ]          *
 *                      1500H  [    a6    ]  [  1700H   ]  *  1500H  [    a6    ]  [  1700H   ]          *
 *                      1600H  [    a2    ]  [   null   ]  *                                             *
 *                      1700H  [    a5    ]  [  1500H   ]  *                                             *
 *                      1800H  [    a3    ]  [  1300H   ]  *                                             *
 *                                                                                                       *
 *          [head][  ]→[a1][  ]→[a2][  ]→[a3][  ]→[a4][  ]→[a5][  ]→[a6][  ]←[tail][  ]           *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      1.	链表以节点的方式存储,像锁链一样一环扣一环，链式存储                                          *
 *      2.	每个节点都有data域和next域，data域负责存储数据，next域指向下一个节点                         *
 *      3.	通过上图可知链表节点不一定连续存储                                                           *
 *      4.	链表有带头结点和没有头结点的                                                                 *
 *      5.	Head节点不存放数据，作用是表示单向链表的头，tail节点也不存放数据，作用是表示单向链表的尾     *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如：登录状态，大小排序等，对线性表的长度或者规模难以估计，频繁做插入删除操作                     *
 *********************************************************************************************************
 * 队列结构                                                                                              *
 *      head:头指针                                                                                      *
 *      tail:尾指针                                                                                      *
 *      Node:节点                                                                                        *
 *           data：程序员自己设置变量，如id，name等                                                      *
 *           next：节点类 如Node                                                                         *
 *********************************************************************************************************
 * 添加（不考虑id顺序）                                                                                  *
 *      1.	创建head节点，表示链表头，tail尾指针指向头节点                                               *
 *      2.	通过尾指针获得链表最后一个元素并将下一节点的地址指向新节点                                   *
 *      3.	tail尾指针向后移动。                                                                         *
 * 添加（根据id顺序）                                                                                    *
 *      1.	通过辅助变量temp找到新节点的插入位置，如果链表有当前编号则插入失败                           *
 *      2.	新的节点的next指向temp.next，即newNode.next=temp.next                                        *
 *      3.	将temp.next指向新节点，即temp.next=newNode                                                   *
 * 插入（表头）                                                                                          *
 *      1.	新节点的next指向头指针的下一个节点                                                           *
 *      2.	头指针指向新节点                                                                             *
 *      3.	判断是否是空链表插入第一个元素时，尾指针后移，如果不是则不移动                               *
 * 查找                                                                                                  *
 *      1.	遍历对比id找到目标节点                                                                       *
 * 有效个数                                                                                              *
 *      1.  方法1.    遍历链表                                                                           *
 *      2.	方法2.    定义一个全局遍历length，增加节点，插入节点+1，删除节点-1                           *
 * 反转链表                                                                                              *
 *      1.	定义一个节点reverseHead                                                                      *
 *      2.	从头到尾遍历链表，每遍历一个节点，就将其取出，放到新链表的最前端                             *
 *      3.	原来的head.next=reverseHead.next                                                             *
 * 查找倒数第n个节点                                                                                     *
 *      1.	接受head节点，以及n                                                                          *
 *      2.	获得链表有效个数length                                                                       *
 *      3.	从链表遍历到（length-n）个，得到倒数第n个节点                                                *
 * 逆序打印                                                                                              *
 *      1.	方式1：对各个节点进行压栈，利用栈的先进后出，实现逆序打印的效果                              *
 *          a)	创建一个栈，将各个节点压入栈中                                                           *
 *          b)	将链表节点压栈                                                                           *
 *          c)	弹栈                                                                                     *
 *      2.	方式2：递归                                                                                  *
 *          a)	创建临时节点                                                                             *
 *          b)	设置递归终止条件temp == null                                                             *
 *          c)	调用方法体本身                                                                           *
 * 修改内容                                                                                              *
 *      1.	根据newNode的id找到要修改的节点，如果没有则警告                                              *
 *      2.	把新节点的内容赋值到要修改的节点                                                             *
 * 删除                                                                                                  *
 *      1.找到需要删除的节点的前一个节点，辅助变量temo指向删除的节点的前一个节点                         *
 *      2. temp的next指向temp的下下个节点的地址，即temp.next=temp.next.next                              *
 *      3.被删除的节点，将不会有其他引用指向，将会被GC回收                                               *
 *********************************************************************************************************
 */

/**
 * 单向链表
 *
 * @ClassName: SingleLinkedList
 * @Author: 张红尘
 * @Date: 2020/1/10 16:41
 * @Version： 1.0
 */
public class SingleLinkedList {
    /**
     * 定义头结点，不存放数据
     */
    private NodeOfSingle head = new NodeOfSingle(0, "");
    /**
     * 定义尾结点，不存放数据
     */
    private NodeOfSingle tail = head;

    /**
     * 返回头节点相当于获得了整个链表
     *
     * @return 头节点
     */
    public NodeOfSingle getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表（不考虑id顺序）
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     *
     * @param newNode 新节点
     */
    public void add(NodeOfSingle newNode) {
        /* 拿到尾指针 */
        NodeOfSingle temp = tail;
        // 此时temp已经指向最后一个节点，把新节点的地址存入temp的next中完成添加节点操作
        temp.next = newNode;
        // 尾指针后移，确保尾指针指向的是最后一个节点
        tail = temp.next;
    }

    /**
     * 添加节点到单向链表（根据id顺序）
     * 1.通过辅助变量temp找到新节点的插入位置，如果链表有当前编号则插入失败
     * 2.新的节点的next指向temp.next，即newNode.next=temp.next
     * 3.将temp.next指向新节点，即temp.next=newNode
     *
     * @param newNode 新节点
     */
    public void addByOrder(NodeOfSingle newNode) {
        //获得指针的头，通过遍历找到插入的位置，即插入节点的前一个节点
        NodeOfSingle temp = head;
        //添加编号的是否存在，默认false
        boolean flag = false;
        while (true) {
            //判断是否到到链表最后也可以用temp==tail判断
            if (temp.next == null) {
                //如果到链表的最后，代表新节点的id最大所以尾结点指向新节点
                tail = newNode;
                break;
            }
            //找到位置，就在temp的后面插入节点
            if (temp.next.id > newNode.id) {
                break;
            } else if (temp.next.id == newNode.id) {
                //添加的节点已经存在
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        //判断flag的值,不能添加说明编号存在
        if (flag) {
            System.out.printf("%d号节点已经存在,不能加入！\n", newNode.id);
        } else {
            /*
                插入链表中，把temp后面的节点赋值给新节点。
                假设链表中有1号节点和2号节点、4号节点、5号节点，需要插入的节点为3号节点
                插入流程：
                1. 3号节点的next存入4号节点的地址
                2. 2号节点的next存入3号节点的地址
                完成插入操作。
                注意：流程不能颠倒，否则4号节点地址丢失,后半段链表数据全部遗失
             */
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }


    /**
     * 插入新节点到链表头
     *
     * @param newNode 新节点
     */
    public void insertTop(NodeOfSingle newNode) {
        //获得链表
        NodeOfSingle temp = head;
        newNode.next = temp.next;
        temp.next = newNode;
        //如果是空链表插入第一个元素时，尾指针后移，如果不是则不移动
        tail = newNode.next == null ? newNode : tail;
    }

    /**
     * 修改节点信息，根据编号来修改即id不能改
     * 1.根据newNode的id来修改节点
     *
     * @param newNode 新节点
     */
    public void update(NodeOfSingle newNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据id
        NodeOfSingle temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while (temp != null) {
            //找到后
            if (temp.id == newNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag是否找到要修改的节点
        if (flag) {
            //找到后修改内容
            temp.name = newNode.name;
        } else {
            System.out.printf("%d号节点不存在,不能修改！\n", newNode.id);
        }
    }

    /**
     * 删除节点
     * 1.temp获得head，遍历找到待删除节点的前一个节点
     * 2.比较时 temp.next.id和待删除节点的id比较
     *
     * @param id 删除节点的id
     */
    public void delete(int id) {
        NodeOfSingle temp = head;
        //标识是否找到待删除节点
        boolean flag = false;
        //遍历链表
        while (temp.next != null) {
            //找到temp下一个节点是否是目标节点
            if (temp.next.id == id) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //找到
        if (flag) {
            //当删除的是最后一个节点的时候将tail指向temp
            tail = tail.id == id ? temp : tail;
            //可以删除
            temp.next = temp.next.next;
            //建议（不一定回收）jvm回收垃圾
            System.gc();
        } else {
            System.out.printf("%d号节点不存在，无法删除！\n", id);
        }
    }

    /**
     * 查找指定id的节点并打印数据
     *
     * @param id 要查找的节点id
     */
    public void select(int id) {
        NodeOfSingle temp = head;
        //标识是否找到待删除节点
        boolean flag = false;
        //遍历链表
        while (temp.next != null) {
            //找到temp下一个节点是否是目标节点
            if (temp.id == id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //找到
        if (flag) {
            //打印
            System.out.println(temp.name);
        } else {
            System.out.printf("%d号节点不存在！\n", id);
        }
    }

    /**
     * 获得单向链表的节点个数
     * 实现的两种方法：
     * 1.遍历链表
     * 2.定义一个全局遍历length，增加节点，插入节点+1，删除节点-1
     *
     * @param head 链表头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(NodeOfSingle head) {
        //判断链表是否为空
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        NodeOfSingle temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找倒数第n个节点
     * 1.接受head节点，以及n
     * 2.获得链表有效个数length
     * 3.从链表遍历到（length-n）个，得到倒数第n个节点
     *
     * @param head 链表头节点
     * @param n    倒数
     */
    public static NodeOfSingle findLastUnknownNode(NodeOfSingle head, int n) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //第一次遍历的到链表长度（节点个数）
        int length = getLength(head);
        //第二次遍历，获得倒数第N个
        if (n <= 0 || n > length) {
            return null;
        }
        //定义赋值变量
        NodeOfSingle temp = head.next;
        for (int i = 0; i < length - n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表
     * 1.	定义一个节点reverseHead
     * 2.	从头到尾遍历链表，每遍历一个节点，就将其取出，放到新链表的最前端
     * 3.	原来的head.next=reverseHead.next
     *
     * @param head 链表头
     */
    public static void reverseList(NodeOfSingle head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //辅助节点,遍历链表
        NodeOfSingle temp = head.next;
        //指向当前节点的下一个节点
        NodeOfSingle next = null;
        //反转链表
        NodeOfSingle reverseHead = new NodeOfSingle(0, "");
        //遍历临时的链表，每遍历一个节点，就将其取出，并放到原来的Head的最前端
        while (temp != null) {
            //暂时保存下一个节点，否则链表丢失
            next = temp.next;
            //将reverseHead的下一个赋值给temp。逻辑参考insertTop方法
            temp.next = reverseHead.next;
            //将temp链接到新的链表上
            reverseHead.next = temp;
            //后移
            temp = next;
        }
        //完成后赋值回head
        head.next = reverseHead.next;
    }

    /**
     * 逆序打印
     * 方式1：对各个节点进行压栈，利用栈的先进后出，实现逆序打印的效果
     * 方式2：递归
     */
    public static void reverse1Print(NodeOfSingle head) {
        //判断是否为空
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<NodeOfSingle> stack = new Stack<>();
        NodeOfSingle temp = head.next;
        //将链表节点压栈
        while (temp != null) {
            stack.push(temp);
            //后移
            temp = temp.next;
        }
        //弹栈
        while (stack.size() > 0) {
            //先进后出
            System.out.println(stack.pop());
        }
    }

    /**
     * 递归逆序打印
     * @param head 头结点
     */
    public static void reverse2Print(NodeOfSingle head) {
        NodeOfSingle temp = head;
        //判断是否为空
        if (temp == null) {
            return;
        }
        reverse2Print(temp.next);
        if (temp.id != 0) {
            System.out.println(temp);
        }
    }

    /**
     * 显示单链表[遍历]
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 拿到头结点的下一个节点
        NodeOfSingle temp = head.next;
        // 不需要打印头结点，所以从头节点的下一个开始遍历
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }


    public static void main(String[] args) {
        // 测试
        // 创建节点
        NodeOfSingle node1 = new NodeOfSingle(1, "刘一");
        NodeOfSingle node2 = new NodeOfSingle(2, "陈二");
        NodeOfSingle node3 = new NodeOfSingle(3, "张三");
        NodeOfSingle node4 = new NodeOfSingle(4, "李四");
        NodeOfSingle node5 = new NodeOfSingle(5, "王五");
        NodeOfSingle node6 = new NodeOfSingle(6, "赵六");
        NodeOfSingle node7 = new NodeOfSingle(7, "孙七");
        NodeOfSingle node8 = new NodeOfSingle(8, "周八");


        // 创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 无顺序加入
        singleLinkedList.add(node1);
        //singleLinkedList.add(node3);
        //注意：乱序插入后在调用addByOrder方法会死循环，要测试乱序插入请注释调用addByOrder的语句
        //singleLinkedList.add(node2);

        // 按id加入
        singleLinkedList.addByOrder(node4);
        singleLinkedList.addByOrder(node6);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node5);
        singleLinkedList.addByOrder(node8);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node7);


        System.out.println("倒数:" + findLastUnknownNode(singleLinkedList.getHead(), 8));

        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));

        //修改前
        System.out.println("-------------修改前-------------");
        singleLinkedList.list();
        System.out.println("-------------修改前-------------");
        //修改节点内容
        NodeOfSingle node9 = new NodeOfSingle(2, "陈二更");
        singleLinkedList.update(node9);
        System.out.println("-------------修改后-------------");
        singleLinkedList.list();
        System.out.println("-------------修改后-------------");

        System.out.println("-------------逆序打印-------------");
        System.out.println("-------------栈-------------");
        reverse1Print(singleLinkedList.getHead());
        System.out.println("-------------递归-------------");
        reverse2Print(singleLinkedList.getHead());
        System.out.println("-------------逆序打印-------------");

        //删除一个节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);
        singleLinkedList.delete(4);
        singleLinkedList.delete(5);

        singleLinkedList.delete(6);
        singleLinkedList.delete(7);
        singleLinkedList.delete(8);
        singleLinkedList.delete(9);

        node3 = new NodeOfSingle(3, "张三1");
        node7 = new NodeOfSingle(7, "孙七");
        node4 = new NodeOfSingle(4, "李四");
        node5 = new NodeOfSingle(5, "王五");
        singleLinkedList.add(node3);
//        singleLinkedList.add(node7);
        singleLinkedList.addByOrder(node5);
        singleLinkedList.addByOrder(node7);
        singleLinkedList.addByOrder(node4);


//
//        singleLinkedList.delete(7);


        node6 = new NodeOfSingle(6, "赵六");
        singleLinkedList.insertTop(node6);

        singleLinkedList.select(3);
        singleLinkedList.select(10);
        System.out.println("有效个数:" + getLength(singleLinkedList.getHead()));

        System.out.println("倒数:" + findLastUnknownNode(singleLinkedList.getHead(), 1));
        reverseList(singleLinkedList.getHead());
        System.out.println("尾指针指向：" + singleLinkedList.tail);
        // 显示
        System.out.println("--------------最终--------------");
        singleLinkedList.list();
        System.out.println("--------------最终--------------");
    }


}
