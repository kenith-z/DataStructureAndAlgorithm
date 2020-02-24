package xyz.hcworld.datastructure.stack;

/**
 * 计算器（中缀表达式）（待重构）
 *
 * @ClassName: Calculator
 * @Author: 张红尘
 * @Date: 2020/2/15 22:19
 * @Version： 1.0
 */
public class Calculator {
    public static void main(String[] args) {
        // 表达式的运算为2*108-1*30+2*30计算错误
        String expression = "3+2*6-2";
        // 创建数栈和符号栈
        StackOfCalculator numStack = new StackOfCalculator(10);
        StackOfCalculator operStack = new StackOfCalculator(10);
        // 定义需要的需要变量
        // 扫描索引
        int index = 0;
        // 要运算的数
        int num1 = 0, num2 = 0;
        // 操作符
        int oper = 0;
        int res = 0;
        // 保存每次扫描的到的char
        char ch = ' ';
        // 用于拼接多位数
        StringBuilder keepNum = new StringBuilder("");


        // 循环扫描expression
        while (true) {
            // 依次的到的expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么然后做相应的处理
            if (operStack.whetherOper(ch)) {
                // 是运算符
                // 判断当前符号栈是否为空
                if (!operStack.sizeEmpty()) {
                    // 如果符号栈中有操作符，进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，
                    //  从数栈中pop出两个数，从符号栈中pop出一个符号，进行运算，将等到的结果入数栈，然后将当前操作符入符号栈
                    if (operStack.priority(ch) >= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 运算结果入数栈
                        numStack.push(res);
                        // 当前操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 为空直接入符号栈
                    operStack.push(ch);
                }
            } else {
                // 1.处理多位数时，不能发现一个数就立即入数栈，因为可能是多位数
                // 2.在处理数，需要向expression的表达式的index后再看几位,如果是数就进行扫描，如果是符号才入栈
                // 处理多位数
                keepNum.append(ch);
                // 如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    // 注意是看后一位，不是index++

                    if (operStack.whetherOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，则入栈keepNum ="1"或者“123”
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        // keepNum清空
                        keepNum = new StringBuilder();
                    }

                }
            }
            // 让index+1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 表达式扫描完毕，按顺序从数栈和符号栈中popo出相应的数和符号，并运行
        while (true) {
            // 如果符号栈为空则计算到最后结果，数栈中只有一个数字[结果]
            if (operStack.sizeEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 入栈
            numStack.push(res);
        }
        // 打印表达式和答案
        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }
}
