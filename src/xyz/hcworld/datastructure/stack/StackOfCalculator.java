package xyz.hcworld.datastructure.stack;

/**
 * @ClassName: StackOfCalculator
 * @Author: 张红尘
 * @Date: 2020/2/15 22:23
 * @Version： 1.0
 */
public class StackOfCalculator {
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
    public StackOfCalculator(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 不出栈的情况下获得栈顶的数据
     * @return
     */
    public int peek(){
        return stack[top];
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
        // 判断栈是否满
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
        // 判断栈是否为空
        if (sizeEmpty()) {
            // 抛出异常
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

    /**
     * 返回运算符的优先级<br>
     * 优先级越高数字越大
     *
     * @param oper 操作符
     * @return
     */
    public int priority(int oper) {
        final char[] symbol = {'*', '/', '+', '-'};
        if (oper == symbol[0] || oper == symbol[1]) {
            return 1;
        } else if (oper == symbol[2] || oper == symbol[3]) {
            return 2;
        } else {
            // 只有加减乘除
            return -1;
        }
    }

    /**
     * 判断是否是运算符
     *
     * @param val
     * @return
     */
    public boolean whetherOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算
     *
     * @param num1 数1
     * @param num2 数2
     * @param oper 操作符
     * @return 结果
     */
    public int cal(int num1, int num2, int oper) {
        // 用于存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                // 注意顺序
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
