package xyz.hcworld.datastructure.linkedlist.node;

/**
 * 双向链表节点
 * @ClassName: NodeOfDouble
 * @Author: 张红尘
 * @Date: 2020/1/29 21:14
 * @Version： 1.0
 */
public class NodeOfDouble {
    //data域
    /**
     * 编号
     */
    public int id;
    /**
     * 姓名
     */
    public String name;

    //next域
    /**
     * 下一节点
     */
    public NodeOfDouble next;
    /**
     * 上一节点
     */
    public NodeOfDouble pre;

    /**
     * 构造器
     * constructor
     * @param id 编号
     * @param name 姓名
     */
    public NodeOfDouble(int id,String name){
        this.id = id;
        this.name = name;
    }

    /**
     * 重写toString，方便显示
     * @return 节点的内容 node of cont
     */
    @Override
    public String toString() {
        return "NodeOfSingle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
