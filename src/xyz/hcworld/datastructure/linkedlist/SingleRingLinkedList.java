package xyz.hcworld.datastructure.linkedlist;


import xyz.hcworld.datastructure.linkedlist.node.NodeOfSingleRing;

/**
 * @ClassName: SingleRingLinkedList
 * @Author: 张红尘
 * @Date: 2020/2/3 22:47
 * @Version： 1.0
 */
public class SingleRingLinkedList {

    /**
     * 第一个节点
     */
    private NodeOfSingleRing first = new NodeOfSingleRing(-1);
    /**
     * 最后一个节点
     */
    private NodeOfSingleRing tail = first;

    /**
     * 创建环形链表
     *
     * @param numbers 节点个数
     */
    public void addNode(int numbers) {
        //数据校验
        if (numbers < 1) {
            System.out.println("id的值不正确");
            return;
        }
        //辅助指针
        NodeOfSingleRing temp = null;
        for (int i = 1; i <= numbers; i++) {
            //根据编号创建节点
            NodeOfSingleRing newNode = new NodeOfSingleRing(i);
            if (i == 1) {
                first = newNode;
                //构建环
                first.setNext(first);
                temp = first;
            } else {
                temp.setNext(newNode);
                newNode.setNext(first);
                temp = newNode;
            }
        }
        //获得最后一个节点
        tail = temp;
    }

    /**
     * 遍历环形链表
     */
    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        //first不能动，借助辅助指针
        NodeOfSingleRing temp = first;
        while (true) {
            System.out.printf("节点编号：%d\n", temp.getId());
            //遍历完成
            if (temp.getNext() == first) {
                break;
            }
            //节点后移
            temp = temp.getNext();
        }
    }

    /**
     * 根据输入，计算节点出列顺序
     * @param startNo  从第几个节点开始
     * @param countNum 计数
     * @param numbers  最初有多少节点
     */
    public void countNodeSequence(int startNo, int countNum, int numbers) {
        //数据校验
        if (first == null || startNo < 1 || startNo > numbers) {
            System.out.println("参数有误，重新输入");
            return;
        }
        //1.创建辅助指针helper，获得循环链表最后节点
        NodeOfSingleRing helper = tail;
        //2.出列前，first和helper移动k-1次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //只有一个节点时结束循环
        while (helper != first) {
            //3.让first和helper同时移动countNum-1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指向的节点为出列节点
            System.out.printf("节点%d出列\n", first.getId());
            //4.first指向的节点出列
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留着圈中的节点为%d\n", first.getId());
    }


    /**
     * 调试方法，可用junit代替
     *
     * @param args
     */
    public static void main(String[] args) {
        SingleRingLinkedList singleRingLinkedList = new SingleRingLinkedList();
        //加入6个节点
        singleRingLinkedList.addNode(6);
        //输出
        singleRingLinkedList.show();
        //测试出圈,出列的顺序是：5，4，6，2，3，1
        singleRingLinkedList.countNodeSequence(1, 5, 6);
    }
}
