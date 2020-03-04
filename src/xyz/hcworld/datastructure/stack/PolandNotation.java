package xyz.hcworld.datastructure.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 *********************************************************************************************************
 * 数据结构之栈的使用-逆波兰计算器                                                                       *
 *********************************************************************************************************
 * 中缀转后缀                                                                                            *
 * 扫描到的元素		S2（栈底→栈顶）	S1（栈底→栈顶）	说明                                         *
 *		1				1	                Null	        数字直接入栈                                 *
 * 		+				1	                +	            S1为空，运算符直接入栈                       *
 * 		(				1	                +(	            左括号，直接入栈                             *
 * 		(				1	                +((	            同上                                         *
 * 		2				12	                +((	            数字                                         *
 * 		+				12	                +((+	        S1栈顶为左括号，运算符直接入栈               *
 * 		3				123	                +((+	        数字                                         *
 * 		)	            123+	            +(	            右括号，弹出运算符直至遇到左括号             *
 * 		*	            123+	            +(*	            S1栈顶为左括号，运算符直接入栈               *
 * 		4	            123+4	            +(*	            数字                                         *
 * 		)	            123+4*	            +	            右括号，弹出运算符直至遇到左括号             *
 * 		-	            123+4*+	            -	            -与+优先级相同，因此弹出+，在压入-           *
 * 		5	            123+4*+5	        -	            数字                                         *
 *  到达最右端       	123+4*+5-	        空	            S1中剩余的运算符                             *
 *********************************************************************************************************
 * 使用场景                                                                                              *
 *      计算器                                                                                           *
 *********************************************************************************************************
 * 中缀转后缀                                                                                            *
 *      1.	初始化一个栈和一个广义表：运算符栈symbol和存储中间结果的线性表medium                         *
 *      2.	从左到右扫描中缀表达式                                                                       *
 *      3.	遇到操作数时，将其插入medium                                                                 *
 *      4.	遇到运算符时，比较其余symbol栈运算符的优先级                                                 *
 *      a)	如果symbol为空，或栈顶运算符为左括号，则直接将此运算符入栈                                   *
 *      b)	若优先级比栈顶运算符的高，也将运算符压入symbol                                               *
 *      c)	否则，将symbol的栈顶的运算符弹出并插入到medium中，在此转到4.a与symbol中新的栈顶运算符相比较  *
 *      5.	遇到括号时：                                                                                 *
 *      a)	左括号：直接压入栈symbol                                                                     *
 *      b)	右括号：依次弹出symbol栈顶的运算符，并插入medium，知道遇到左括号为止，此时将这一对括号丢弃   *
 *      6.	重复步骤2至5，直到表达式的最右边                                                             *
 *      7.	将symbol中剩余的运算符一次弹出并插入medium                                                   *
 *      8.	遍历medium即为后缀表达式                                                                     *
 * 逆波兰计算器                                                                                          *
 *      1.从左到右扫描，将3和4压入栈；                                                                   *
 *      2.遇到+运算符，隐藏弹出4和3（4为栈顶元素，3为次顶元素），计算3+4的值，得到7，再将7入栈；         *
 *      3.将5入栈；                                                                                      *
 *      4.接下来*运算符，因此弹出5和7，极端出7*5=35，将35入栈；                                          *
 *      5.将6入栈;                                                                                       *
 *      6.最后-运算符，计算出35-6的值，得出最终结果29                                                    *
 *********************************************************************************************************
 */


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

        // 中缀表达式转后缀表达式
        // 1.1+((2+3)*4)-5转成1 2 3 + 4 * + 5 -
        // 2.因为直接对字符串进行操作不方便，将字符串转成中缀的表达式对应的list
        // 即"1+((2+3)*4)-5"=>ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        // 3.将得到的中缀表达式对应的List转成=》后缀表达式对应的List
        // 即[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] =》[1, 2, 3, +, 4, *, +, 5, -]
        String expression = "1+((2+3)*4)-5";

        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀：" + infixExpressionList.toString());
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀：" + parseSuffixExpressionList.toString());
        //计算后缀表达式
        System.out.println("计算结果=" + calculate(parseSuffixExpressionList));

        System.out.println("------------------------------------");

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
        System.out.println("计算结果=" + calculate(rpnList));
    }

    /**
     * 将中缀表达式转成对应的List
     *
     * @param s 表达式
     * @return 存放中缀的表达式的list
     */
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        // 遍历s的指针
        int i = 0;
        // 对多位数的拼接
        StringBuilder str;
        // 遍历到一个字符就放进c
        char c;
        do {
            // 如果c是非数字，需要加入到ls asii:48=0,57=9
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                // i后移
                i++;
            } else {
                // 将str清空，考虑到多位数的问题
                str = new StringBuilder();
                while (i < s.length() && (c = s.charAt(i)) > 48 && (c = s.charAt(i)) < 57) {
                    // 拼接
                    str.append(c);
                    i++;
                }
                ls.add(str.toString());
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 中缀转后缀
     * @param ls 中缀list
     * @return 后缀list
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 符号栈
        Stack<String> symbol = new Stack<>();
        // 中间结果
        List<String> medium = new ArrayList<>();
        String[] contrast = {"(", ")"};
        // 遍历ls
        for (String item : ls) {
            // 如果是一个数，就加入到medium
            if (item.matches("\\d+")) {
                medium.add(item);
            } else if (contrast[0].equals(item)) {
                // 如果是左括号
                symbol.push(item);
            } else if (contrast[1].equals(item)) {
                // 如果是右括号，则依次弹出symbol的运算符，并压入medium，直到遇到左括号为止，然后将这对括号丢弃
                while (!contrast[0].equals(symbol.peek())) {
                    medium.add(symbol.pop());
                }
                // 将(弹出symbol，消除小括号
                symbol.pop();
            } else {
                // 当item的优先级小于等于symbol栈顶运算符，
                // 将symbol的栈顶运算符弹出并加入到medium中，再转到4.a与symbol中新的栈顶运算符进行比较
                while (symbol.size() != 0 && getValue(symbol.peek()) >= getValue(item)) {
                    medium.add(symbol.pop());
                }
                //将item压入栈中
                symbol.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (symbol.size() != 0) {
            medium.add(symbol.pop());
        }
        //因为是存放到list中，按顺序输出就是对于的后缀表达式
        return medium;
    }

    /**
     * 比较运算符优先级
     *
     * @param operation 运算符
     * @return 级别
     */
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default:
                if (!("(".equals(operation) || ")".equals(operation))) {
                    System.out.println("不存在该运算符");
                }
                break;
        }
        return result;
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
     *
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
                stack.push(res + "");
            }
        }
        // 最后留着stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}
