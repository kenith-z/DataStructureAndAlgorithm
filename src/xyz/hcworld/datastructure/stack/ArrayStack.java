package xyz.hcworld.datastructure.stack;

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
