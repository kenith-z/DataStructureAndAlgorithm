package xyz.hcworld.datastructure.linkedlist.node;

/**
 * 单向链表的节点
 * Node of single linked list
 * @ClassName: NodeOfSingle
 * @Author: 张红尘
 * @Date: 2020/1/10 16:45
 * @Version： 1.0
 */
public class NodeOfSingleRing {

    //data域
    /**
     * 编号
     */
    private int id;


    //next域
    /**
     * 下一节点
     */
    private NodeOfSingleRing next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeOfSingleRing getNext() {
        return next;
    }

    public void setNext(NodeOfSingleRing next) {
        this.next = next;
    }

    /**
     * 构造器
     * constructor
     * @param id 编号
     */
    public NodeOfSingleRing(int id){
        this.id = id;
    }

    /**
     * 重写toString，方便显示
     * @return 节点的内容 node of cont
     */
    @Override
    public String toString() {
        return "NodeOfSingle{" +
                "id=" + id +
                '}';
    }
}
