package xyz.hcworld.datastructure.linkedlist.node;

/**
 * 单向链表的节点
 * Node of single linked list
 * @ClassName: NodeOfSingle
 * @Author: 张红尘
 * @Date: 2020/1/10 16:45
 * @Version： 1.0
 */
public class NodeOfSingle {

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
    public NodeOfSingle next;

    /**
     * 构造器
     * constructor
     * @param id 编号
     * @param name 姓名
     */
    public NodeOfSingle(int id,String name){
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
