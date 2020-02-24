package xyz.hcworld.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 *
 * @ClassName: PolandNotation
 * @Author: 张红尘
 * @Date: 2020/2/24 21:09
 * @Version： 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 定义一个逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        // 11*20+(23-15) => 11 20 * 23 + 15 -
        // 11*20+23/15 => 11 20 * 23 15 / +
        // 为了方便数字和符号判断使用空格隔开
        String suffixExpression = "11 20 * 23 15 / +";
        // 1.现将3 4 + 5 * 6 -放到ArrayList中
        // 2.将ArrayList传递给一个方法，遍历list配合栈完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("表达式=" + rpnList);
        System.out.println("计算结果="+calculate(rpnList));
    }

    /**
     * 将逆波兰表达式，依次将数据和运算符放入到ArrayList中
     *
     * @param suffixExpresion 后缀表达式
     * @return 拆分后的表达式
     */
    public static List<String> getListString(String suffixExpresion) {
        // 分割suffixExpression
        String[] split = suffixExpresion.split(" ");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        return list;
    }

    /**
     * 完成对逆波兰表达式的运算
     * 1.从左到右扫描，将3和4压入栈；
     * 2.遇到+运算符，隐藏弹出4和3（4为栈顶元素，3为次顶元素），计算3+4的值，得到7，再将7入栈；
     * 3.将5入栈；
     * 4.接下来*运算符，因此弹出5和7，极端出7*5=35，将35入栈；
     * 5.将6入栈;
     * 6.最后-运算符，计算出35-6的值，得出最终结果29
     * @param list 拆分后的表达式
     * @return 结果或者异常
     */
    public static int calculate(List<String> list) {
        // 创建栈，只需要一个栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String item : list) {
            // 使用正则表达式取数,匹配多位数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                // res入栈
                stack.push(res+"");
            }
        }
        // 最后留着stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
