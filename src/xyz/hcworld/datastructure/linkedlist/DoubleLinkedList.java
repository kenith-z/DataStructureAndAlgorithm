package xyz.hcworld.datastructure.linkedlist;

import xyz.hcworld.datastructure.linkedlist.node.NodeOfDouble;

/*
 *********************************************************************************************************
 * 数据结构之双向链表                                                                                    *
 *********************************************************************************************************
 *          [   head    ]  [   node1   ]  [   node2   ]  [   node3   ]  [   node4   ] ←[tail]           *
 *          [next：node1]→[next：node2]→[next：node3]→[next：node4]→[next：null ]                    *
 *          [pre： null ]←[pre： head ]←[pre： node1]←[pre： node2]←[pre： node3]                    *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      1.	与单项链表相似，但是多了个后驱节点（指向上一个节点）                                         *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如：登录状态，大小排序等，对线性表的长度或者规模难以估计，频繁做查找、插入、删除操作             *
 *********************************************************************************************************
 * 队列结构                                                                                              *
 *      head:头指针                                                                                      *
 *      tail:尾指针                                                                                      *
 *      Node:节点                                                                                        *
 *           data：程序员自己设置变量，如id，name等                                                      *
 *           next：指向下一个节点                                                                        *
 *           pre:  指向上一个节点                                                                        *
 *********************************************************************************************************
 * 添加（最后）                                                                                          *
 *      1.	获得链表的尾指针tail  temp=tail                                                              *
 *      2.	将temp的next指向新节点 temp.next=newNode;                                                    *
 *      3.	新节点的pre指向temp  newNode.pre=temp;                                                       *
 *      4.	尾指针指向新节点 tail=newNode;                                                               *
 * 添加（根据id顺序）                                                                                    *
 *      1.	找到id比新节点id大的节点的前一个节点temp，如果没有则新节点id最大                             *
 *      2.	新节点的pre指向temp节点 newNode.pre=temp;                                                    *
 *      3.	新节点指向head的下一个节点 newNode.next = temp.next;                                         *
 *      4.	temp节点的next指向新节点 temp.next = newNode;                                                *
 *      当新节点的下一个节点是否存在                                                                     *
 *      5.	存在，新节点的下一个节点的pre指向新节点                                                      *
 *      6.	不存在，代表新节点为最后一个节点，尾指针指向新节点                                           *
 * 插入（表头）                                                                                          *
 *      1.	获得头节点                                                                                   *
 *      2.	新节点的pre指向头节点 newNode.pre=temp;                                                      *
 *      3.	新节点指向head的下一个节点 newNode.next = temp.next;                                         *
 *      4.	头节点的的next指向新节点 temp.next = newNode;                                                *
 *      当新节点的下一个节点是否存在                                                                     *
 *      5.	存在，新节点的下一个节点的pre指向新节点                                                      *
 *      6.	不存在，代表新节点为最后一个节点，尾指针指向新节点                                           *
 * 查找                                                                                                  *
 *      1.	遍历对比id找到目标节点，但是可以向前也可以向后查找                                           *
 * 修改内容                                                                                              *
 *      1.	根据newNode的id找到要修改的节点，如果没有则警告                                              *
 *      2.	把新节点的内容赋值到要修改的节点                                                             *
 * 删除                                                                                                  *
 *      1.	遍历找到要查找的节点temp                                                                     *
 *      2.	如果不是，把temp的上一个节点的next指向temp的下一个节点，                                     *
 *          如果是最后一个则指向空 temp.pre.next=temp.next;                                              *
 *      判断temp是否为最后一个节点                                                                       *
 *      3.	如果是，tail指向上一个节点 if(temp.next==null) tail=temp.pre;                                *
 *      4.	如果不是，把temp下一个节点的pre指向temp的上一个节点 temp.next.pre=temp.pre;                  *
 *      5.	temp的pre和next指向null 完成后temp无指向为废弃节点gc会回收 temp.next = null;temp.pre = null; *
 *********************************************************************************************************
 */

/**
 * @ClassName: DoubleLinkedList
 * @Author: 张红尘
 * @Date: 2020/1/29 21:13
 * @Version： 1.0
 */
public class DoubleLinkedList {
    private NodeOfDouble head = new NodeOfDouble(0, "");
    private NodeOfDouble tail = head;

    /**
     * 返回头节点
     *
     * @return 头节点
     */
    public NodeOfDouble getHead() {
        return head;
    }

    /**
     * 返回尾节点
     *
     * @return 尾结点
     */
    public NodeOfDouble getTail() {
        return tail;
    }

    /**
     * 插入新节点到链表头
     * 1.	获得头节点
     * 2.	新节点的pre指向头节点 newNode.pre=temp;
     * 3.	新节点指向head的下一个节点 newNode.next = temp.next;
     * 4.	头节点的的next指向新节点 temp.next = newNode;
     * 当新节点的下一个节点是否存在
     * 5.	存在，新节点的下一个节点的pre指向新节点
     * 6.	不存在，代表新节点为最后一个节点，尾指针指向新节点
     *
     * @param newNode 新节点
     */
    public void insertTop(NodeOfDouble newNode) {
        // 获得链表
        NodeOfDouble temp = head;
        // 新节点的pre指向头节点
        newNode.pre = temp;
        // 新节点指向head的下一个节点
        newNode.next = temp.next;
        // 头节点的的next指向新节点
        temp.next = newNode;
        //当新节点的下一个节点是否存在
        if (newNode.next != null) {
            // 存在，新节点的下一个节点的pre指向新节点
            newNode.next.pre = newNode;
        } else {
            //不存在，代表新节点为最后一个节点，尾指针指向新节点
            tail = newNode;
        }
    }

    /**
     * 添加节点到单向链表（根据id顺序）
     * 1.	找到id比新节点id大的节点的前一个节点temp，如果没有则新节点id最大
     * 2.	新节点的pre指向temp节点 newNode.pre=temp;
     * 3.	新节点指向head的下一个节点 newNode.next = temp.next;
     * 4.	temp节点的next指向新节点 temp.next = newNode;
     * 当新节点的下一个节点是否存在
     * 5.	存在，新节点的下一个节点的pre指向新节点
     * 6.	不存在，代表新节点为最后一个节点，尾指针指向新节点
     *
     * @param newNode 新节点
     */
    public void addByOrder(NodeOfDouble newNode) {
        //获得指针的头，通过遍历找到插入的位置，即插入节点的前一个节点
        NodeOfDouble temp = head;
        //添加编号的是否存在，默认false
        boolean flag = false;
        //最后节点的标识符，默认false
        boolean tailFlag = false;
        while (true) {
            //判断是否到到链表最后也可以用temp==tail判断
            if (temp.next == null) {
                //如果到链表的最后，代表新节点的id最大所以尾结点指向新节点
                tail = newNode;
                tailFlag = true;
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
            // 新节点的pre指向头节点
            newNode.pre = temp;
            // 新节点指向head的下一个节点
            newNode.next = temp.next;
            // 头节点的的next指向新节点
            temp.next = newNode;
            //当新节点的下一个节点是否存在
            if (!tailFlag) {
                // 存在，新节点的下一个节点的pre指向新节点
                newNode.next.pre = newNode;
            }
        }
    }

    /**
     * 添加节点到双向链表的最后
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新的节点
     * 3.新节点的pre指向temp
     *
     * @param newNode 新节点
     */
    public void add(NodeOfDouble newNode) {
        /* 拿到尾指针 */
        NodeOfDouble temp = tail;
        // 此时temp已经指向最后一个节点，把新节点的地址存入temp的next中完成添加节点操作
        temp.next = newNode;
        // 新节点的pre指向temp节点
        newNode.pre = temp;
        // 尾指针后移，确保尾指针指向的是最后一个节点
        tail = newNode;
    }

    /**
     * 修改节点信息，根据编号来修改即id不能改，和单向链表一样
     * 1.根据newNode的id来修改节点
     *
     * @param newNode 新节点
     */
    public void update(NodeOfDouble newNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据id
        NodeOfDouble temp = head.next;
        // 表示是否找到该节点
        boolean flag = false;
        while (temp != null) {
            // 找到后
            if (temp.id == newNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag是否找到要修改的节点
        if (flag) {
            // 找到后修改内容
            temp.name = newNode.name;
        } else {
            System.out.printf("%d号节点不存在,不能修改！\n", newNode.id);
        }
    }

    /**
     * 删除节点（双向链表可自我删除，无需辅助节点）
     * 1.	遍历找到要查找的节点temp
     * 2.	如果不是，把temp的上一个节点的next指向temp的下一个节点，如果是最后一个则指向空
     * 判断temp是否为最后一个节点
     * 3.	如果是，tail指向上一个节点 if(temp.next==null)
     * 4.	如果不是，把temp下一个节点的pre指向temp的上一个节点
     * 5.	temp的pre和next指向null 完成后temp无指向为废弃节点gc会回收
     *
     * @param id 删除节点的id
     */
    public void delete(int id) {
        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法杀出");
            return;
        }
        NodeOfDouble temp = head.next;
        // 标识是否找到待删除节点
        boolean flag = false;
        // 遍历链表
        while (temp != null) {
            // 找到temp下一个节点是否是目标节点
            if (temp.id == id) {
                // 找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 1.找到要查找的节点temp
        if (flag) {
            // 2.把temp的上一个节点的next指向temp的下一个节点，如果是最后一个则指向空
            temp.pre.next = temp.next;
            // 判断temp是否为最后一个节点，如果是tail指向上一个节点
            if (tail.id == id) {
                // 3.将尾结点指向temp的上一节点
                tail = temp.pre;
            } else {
                // 4.不是尾结点把temp下一个节点的pre指向temp的上一个节点
                temp.next.pre = temp.pre;
            }
            // 5.temp的pre和next指向null 完成后temp无指向为废弃节点gc会回收
            temp.next = null;
            temp.pre = null;
            // 建议（不一定回收）jvm回收垃圾
            System.gc();
        } else {
            System.out.printf("%d号节点不存在，无法删除！\n", id);
        }
    }

    /**
     * 显示双链表[遍历]
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 拿到头结点的下一个节点
        NodeOfDouble temp = head.next;
        // 不需要打印头结点，所以从头节点的下一个开始遍历
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        System.out.println("双向链表的测试");

        // 创建节点
        NodeOfDouble node1 = new NodeOfDouble(1, "刘一");
        NodeOfDouble node2 = new NodeOfDouble(2, "陈二");
        NodeOfDouble node3 = new NodeOfDouble(3, "张三");
        NodeOfDouble node4 = new NodeOfDouble(4, "李四");
        NodeOfDouble node5 = new NodeOfDouble(5, "王五");
        NodeOfDouble node6 = new NodeOfDouble(6, "赵六");
        NodeOfDouble node7 = new NodeOfDouble(7, "孙七");
        NodeOfDouble node8 = new NodeOfDouble(8, "周八");

        System.out.println("无序添加测试");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node6);
        doubleLinkedList.add(node4);
        doubleLinkedList.list();
        System.out.println("tail:"+doubleLinkedList.getTail().toString());
        // 测试空链表时插入
        // doubleLinkedList = new DoubleLinkedList();
        System.out.println("头部插入测试");
        doubleLinkedList.insertTop(node8);
        doubleLinkedList.list();
        System.out.println("tail:"+doubleLinkedList.getTail().toString());

        System.out.println("有序插入测试");
        node1 = new NodeOfDouble(1, "刘一");
        node2 = new NodeOfDouble(2, "陈二");
        node3 = new NodeOfDouble(3, "张三");
        node4 = new NodeOfDouble(4, "李四");
        node5 = new NodeOfDouble(5, "王五");
        node6 = new NodeOfDouble(6, "赵六");
        node7 = new NodeOfDouble(7, "孙七");
        node8 = new NodeOfDouble(8, "周八");
        doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(node8);
        doubleLinkedList.addByOrder(node1);
        doubleLinkedList.addByOrder(node2);
        doubleLinkedList.addByOrder(node3);
        doubleLinkedList.addByOrder(node4);
        doubleLinkedList.addByOrder(node6);
        doubleLinkedList.addByOrder(node5);
        doubleLinkedList.addByOrder(node7);
        doubleLinkedList.list();
        System.out.println("tail:"+doubleLinkedList.getTail().toString());

        System.out.println("修改测试");
        doubleLinkedList.update(new NodeOfDouble(6,"赵6"));
        doubleLinkedList.list();
        System.out.println("tail:"+doubleLinkedList.getTail().toString());

        System.out.println("删除测试");
        doubleLinkedList.delete(1);
        doubleLinkedList.delete(5);
        doubleLinkedList.delete(8);
        //测试节点是否next和pre都为空
        //doubleLinkedList.addByOrder(node5);
        //doubleLinkedList.addByOrder(node8);
        doubleLinkedList.list();
        System.out.println("tail:"+doubleLinkedList.getTail().toString());
    }
}
