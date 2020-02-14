package xyz.hcworld.datastructure.stack;


/*
 *********************************************************************************************************
 * 数据结构之栈                                                                                          *
 *********************************************************************************************************
 *     入栈                                  出栈                                                        *
 *     [   ]    [   ]    [   ]    [   ]      [   ]    [   ]    [   ]    [   ]                            *
 *     [   ] → [   ] → [   ] → [ 3 ]      [ 3 ] → [   ] → [   ] → [   ]                            *
 *     [   ]    [   ]    [ 2 ]    [ 2 ]      [ 2 ]    [ 2 ]    [   ]    [   ]                            *
 *     [   ]    [ 1 ]    [ 1 ]    [ 1 ]      [ 1 ]    [ 1 ]    [ 1 ]    [   ]                            *
 *********************************************************************************************************
 * 思路                                                                                                  *
 *      栈是一个先入后出的有序列表                                                                       *
 *      栈是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。                       *
 *      允许插入和删除的一端，变化的另一端，称为栈顶（top），另一端为固定的一端称为栈底（bottom）        *
 *      最先放入栈中的元素在栈底，最后放入的元素在栈顶，删除元素则最先放入的最后删除，最后放入的最先删除 *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      如逆序输出、语法检查，符号成对出现、数制转换等                                                   *
 *********************************************************************************************************
 * 稀疏数组结构                                                                                          *
 *      使用数组模拟栈                                                                                   *
 *      定义一个top来表示栈顶，初始化为-1                                                                *
 *********************************************************************************************************
 * 入栈                                                                                                  *
 *      有数据加入栈时，top++;stack[top]=data;                                                           *
 * 出栈                                                                                                  *
 *      int value = stack[top];top--;return value;                                                       *
 *********************************************************************************************************
 */

/**
 * 栈
 *
 * @ClassName: ArrayStack
 * @Author: 张红尘
 * @Date: 2020/2/7 16:56
 * @Version： 1.0
 */
public class ArrayStack {
    /**
     * 栈的最大值
     */
    private int maxSize;
    /**
     * 数组模拟的栈，数据就存放在这
     */
    private int[] stack;
    /**
     * 栈顶，初始化为-1
     */
    private int top = -1;

    /**
     * 栈的构造器
     *
     * @param maxSize
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 判断栈是否已满
     *
     * @return 满true 不满false
     */
    public boolean sizeFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     *
     * @return 空true 不空false
     */
    public boolean sizeEmpty() {
        return top == -1;
    }

    /**
     * 入栈-push
     *
     * @param value 要入栈的数据
     */
    public void push(int value) {
        //判断栈是否满
        if (sizeFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    /**
     * 出栈-pop
     *
     * @return 要出栈的数据
     */
    public int pop() {
        //判断栈是否为空
        if (sizeEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    /**
     * 遍历栈,从栈顶到栈底
     */
    public void list() {
        if (sizeEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        StringBuilder sb = new StringBuilder();

        stack.list();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.list();
        try {
            sb.append(stack.pop());
            sb.append(stack.pop());
            sb.append(stack.pop());
            sb.append(stack.pop());
            sb.append(stack.pop());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        stack.list();
        System.out.println(sb.toString());

    }

}
